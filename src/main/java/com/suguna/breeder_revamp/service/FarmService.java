package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.model.BranchUser;

import java.util.ArrayList;

public interface FarmService {
    ArrayList<BranchUser> getBranchUsers(BranchRequest branchRequest);

    ArrayList<BranchUser.SupervisorDetails> getSupervisorDetails(BranchRequest branchRequest);

    ArrayList<BranchUser.ShedDetails> getShedDetails(String branchId);
}
