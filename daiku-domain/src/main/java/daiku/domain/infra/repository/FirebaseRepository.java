package daiku.domain.infra.repository;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import daiku.domain.infra.enums.AccountType;
import daiku.domain.infra.model.param.firestore.BusinessDataAsync;
import daiku.domain.utils.FirebaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FirebaseRepository {

    @Autowired
    FirebaseClient client;

    public void asyncBusinessData(BusinessDataAsync async) {
    }

    public void deleteAccount(String uid) throws FirebaseAuthException {

        client.auth().deleteUser(uid);
    }

    public void reUpdateAccount(String uid) throws FirebaseAuthException {
        client.auth().updateUser(new UserRecord.UpdateRequest(uid)
                .setDisabled(false));
    }

    public void accountClaims(String uid) throws FirebaseAuthException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("account_type", AccountType.GENERAL.getValue());
        client.auth().setCustomUserClaims(uid, claims);
    }


}
