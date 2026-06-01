package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.model.TransferPlace;


import java.util.ArrayList;

public interface TransferService {
    ArrayList<TransferPlace> getTransferPlace(BranchRequest branchRequest);

    ArrayList<TransferPlace.EggItemDetails> getEggItemMaster(BranchRequest branchRequest);

    ArrayList<TransferPlace.FeedItemDetails> getFeedItemMaster(BranchRequest branchRequest);

    ArrayList<TransferPlace.MedicineVaccineDetails> getMedicineVaccineMaster(BranchRequest branchRequest);
}
