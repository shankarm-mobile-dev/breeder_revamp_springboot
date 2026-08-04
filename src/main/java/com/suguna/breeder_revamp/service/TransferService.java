package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.dto.PlanRequest;
import com.suguna.breeder_revamp.dto.SUGMAIGPPSTRANS_HDRDto;
import com.suguna.breeder_revamp.dto.TransferPlanDto;
import com.suguna.breeder_revamp.model.BranchUser;
import com.suguna.breeder_revamp.model.TransferPlace;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

public interface TransferService {
    ArrayList<TransferPlace> getTransferPlace(BranchRequest branchRequest);

    ArrayList<TransferPlace.EggItemDetails> getEggItemMaster(BranchRequest branchRequest);

    ArrayList<TransferPlace.FeedItemDetails> getFeedItemMaster(BranchRequest branchRequest);

    ArrayList<TransferPlace.MedicineVaccineDetails> getMedicineVaccineMaster(BranchRequest branchRequest);

    ArrayList<TransferPlace.TransferInHdr> getTransferInHdr(BranchRequest branchRequest);

    String saveTransOut(ArrayList<SUGMAIGPPSTRANS_HDRDto> entry);

    ArrayList<TransferPlace> getTransferPlanPlace(BranchRequest branchRequest);

    ArrayList<BranchUser> getAllBranch(BranchRequest branchRequest);

    String saveTransPlan(TransferPlanDto entry);

    ArrayList<TransferPlace.VehicleGateInDetails> getEggGateInDetails(BranchRequest branchRequest);

    ArrayList<TransferPlace.VehicleGateOutDetails> getEggGateOutDetails(BranchRequest branchRequest);

    ArrayList<TransferPlace.HatcheryPlanDetails> getEggHatcheryPlanDetails(BranchRequest branchRequest);

    ArrayList<TransferPlace.TransferPlanDetails> getPlanDetails(BranchRequest branchRequest);

    ArrayList<TransferPlace.EggItemStockDetails> getEggStockDetails(BranchRequest branchRequest);


    String saveGateInDetails(PlanRequest branchRequest, List<MultipartFile> imageFile);

    String saveGateOutDetails(PlanRequest branchRequest, List<MultipartFile> imageFile);
}
