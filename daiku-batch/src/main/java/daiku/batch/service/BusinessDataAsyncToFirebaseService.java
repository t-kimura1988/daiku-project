package daiku.batch.service;

import daiku.domain.infra.model.param.firestore.BusinessDataAsync;
import daiku.domain.infra.repository.AccountRepository;
import daiku.domain.infra.repository.FirebaseRepository;
import daiku.domain.infra.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BusinessDataAsyncToFirebaseService {
    @Autowired
    private FirebaseRepository firebaseRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GoalRepository goalRepository;

    public void asyncBusinessDataFirebase() {
        firebaseRepository.asyncBusinessData(BusinessDataAsync.builder()
                .accountCount(accountRepository.accountCount())
                .goalCount(goalRepository.selectGoalCount(LocalDate.now())).build());

    }
}
