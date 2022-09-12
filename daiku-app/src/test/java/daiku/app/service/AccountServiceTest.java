package daiku.app.service;

import daiku.app.DaikuApplication;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.infra.entity.TAccounts;
import daiku.domain.infra.enums.DelFlg;
import daiku.domain.infra.repository.AccountRepository;
import daiku.domain.utils.FirebaseClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@Slf4j
@SpringBootTest(classes = DaikuApplication.class)
@AutoConfigureMockMvc
@SpringJUnitWebConfig
public class AccountServiceTest {

    @Autowired
    @InjectMocks
    private AccountService accountService;

    @SpyBean
    private AccountRepository accountRepository;

    @SpyBean
    private FirebaseClient firebaseClient;

    @Test
    @DisplayName("selectByUidでデータが取得できなかった場合")
    void test1() {
        try {

            doReturn(Optional.empty()).when(accountRepository).selectByUid(any());
            accountService.showService("test");
        } catch(GoenNotFoundException e) {
            assertEquals(e.getMessage(), "goal detail info no found");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("アカウント存在するけど削除されている")
    void test2() {

        try {
            TAccounts accounts = new TAccounts();
            accounts.setId(1L);
            accounts.setUid("11111");
            accounts.setDelFlg(DelFlg.DELETED);
            doReturn(Optional.of(accounts)).when(accountRepository).selectByUid(any());
            accountService.showService("test");
        } catch(GoenIntegrityException e) {
            assertEquals(e.getMessage(), "アカウントが削除済み");
            assertEquals(e.getErrorCd(), "E0001");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("アカウント存在する_正常")
    void test3() {

        try {
            TAccounts accounts = new TAccounts();
            accounts.setId(1L);
            accounts.setUid("11111");
            accounts.setDelFlg(DelFlg.NOT_DELETED);
            doReturn(Optional.of(accounts)).when(accountRepository).selectByUid(any());
            accountService.showService("test");
        } catch (Exception e) {
            fail();
        }
    }
}
