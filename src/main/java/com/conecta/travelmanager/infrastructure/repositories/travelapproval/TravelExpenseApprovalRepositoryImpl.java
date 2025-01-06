package com.conecta.travelmanager.infrastructure.repositories.travelapproval;

import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;
import com.conecta.travelmanager.domain.repositories.TravelExpenseApprovalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TravelExpenseApprovalRepositoryImpl implements TravelExpenseApprovalRepository {
    private final JpaTravelExpenseApprovalRepository jpaTravelExpenseApprovalRepository;

    public TravelExpenseApprovalRepositoryImpl(JpaTravelExpenseApprovalRepository jpaTravelExpenseApprovalRepository) {
        this.jpaTravelExpenseApprovalRepository = jpaTravelExpenseApprovalRepository;
    }

    @Override
    public Optional<TravelExpenseApproval> findById(long travelExpenseApprovalId) {
        return this.jpaTravelExpenseApprovalRepository.findById(travelExpenseApprovalId);
    }

    @Override
    public TravelExpenseApproval create(TravelExpenseApproval travelExpenseApproval) {
        return this.jpaTravelExpenseApprovalRepository.save(travelExpenseApproval);
    }

    @Override
    public TravelExpenseApproval update(TravelExpenseApproval travelExpenseApproval) {

        return this.jpaTravelExpenseApprovalRepository.save(travelExpenseApproval);
    }

    @Override
    public List<TravelExpenseApproval> findAll() {
        return this.jpaTravelExpenseApprovalRepository.findAll();
    }

    @Override
    public List<TravelExpenseApproval> findByApprovalStatus(ApprovalStatus approvalStatus) {
        return this.jpaTravelExpenseApprovalRepository.findByApprovalStatus(approvalStatus);
    }
}
