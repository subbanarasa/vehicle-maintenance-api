package com.interview.assignment.vehiclemaintenance.service.impl;

import com.google.common.collect.Lists;
import com.interview.assignment.vehiclemaintenance.exception.DuplicateException;
import com.interview.assignment.vehiclemaintenance.exception.ResourceNotFoundException;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.model.VehicleOwnership;
import com.interview.assignment.vehiclemaintenance.repository.UserRepository;
import com.interview.assignment.vehiclemaintenance.repository.VehicleOwnershipRepository;
import com.interview.assignment.vehiclemaintenance.repository.VehicleRepository;
import com.interview.assignment.vehiclemaintenance.service.VehicleOwnershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleOwnershipServiceImpl implements VehicleOwnershipService {

    private static Logger logger = LoggerFactory.getLogger(VehicleOwnershipService.class);


    private VehicleRepository vehicleRepository;

    private UserRepository userRepository;

    private VehicleOwnershipRepository vehicleOwnershipRepository;

    public VehicleOwnershipServiceImpl(VehicleRepository vehicleRepository, UserRepository userRepository, VehicleOwnershipRepository vehicleOwnershipRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.vehicleOwnershipRepository = vehicleOwnershipRepository;
    }

    @Override
    public VehicleOwnership saveVehicleOwnership(VehicleOwnership vehicleOwnership) {
        if (!vehicleRepository.existsById(vehicleOwnership.getVehicleId())) {
            throw new ResourceNotFoundException("Vehicle for ID " + vehicleOwnership.getVehicleId() + " is not found");
        }

        if (!userRepository.existsById(vehicleOwnership.getOwnerId())) {
            throw new ResourceNotFoundException("Owner for ID " + vehicleOwnership.getOwnerId() + " is not found");
        }

        VehicleOwnership savedVehicleOwnership = vehicleOwnershipRepository.findByOwnerIdAndVehicleId(vehicleOwnership.getOwnerId(), vehicleOwnership.getVehicleId());

        if (savedVehicleOwnership == null) {
            return vehicleOwnershipRepository.save(vehicleOwnership);
        } else {
            throw new DuplicateException("Vehicle ID" + vehicleOwnership.getVehicleId() + " is already associated with owner ID" + vehicleOwnership.getOwnerId());
        }

    }

    @Override
    public List<Vehicle> getVehiclesForOwner(Long ownerId) {
        if (!userRepository.existsById(ownerId)) {
            throw new ResourceNotFoundException("Owner for ID " + ownerId + " is not found");
        }
        List<VehicleOwnership> ownershipList = vehicleOwnershipRepository.findAllByOwnerId(ownerId);

        if (ownershipList != null && !ownershipList.isEmpty()) {
            List<Long> vehicles = ownershipList.stream().map(vehicleOwnership -> vehicleOwnership.getVehicleId()).collect(Collectors.toList());
            if (logger.isDebugEnabled()) {
                logger.debug("Vehicles for ownerId is " + vehicles);
            }
            return Lists.newArrayList(vehicleRepository.findAllById(vehicles));
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public VehicleOwnership updateVehicleOwnership(VehicleOwnership vehicleOwnership) {
        if (!vehicleRepository.existsById(vehicleOwnership.getVehicleId())) {
            throw new ResourceNotFoundException("Vehicle for ID " + vehicleOwnership.getVehicleId() + " is not found");
        }

        if (!userRepository.existsById(vehicleOwnership.getOwnerId())) {
            throw new ResourceNotFoundException("Owner for ID " + vehicleOwnership.getOwnerId() + " is not found");
        }
        VehicleOwnership savedVehicleOwnership = vehicleOwnershipRepository.findByVehicleId(vehicleOwnership.getVehicleId());
        logger.info("Saved vehicle ownership::" + savedVehicleOwnership);
        if (savedVehicleOwnership != null) {
            savedVehicleOwnership.setOwnerId(vehicleOwnership.getOwnerId());
            return vehicleOwnershipRepository.save(savedVehicleOwnership);
        } else {
            throw new ResourceNotFoundException("Vehicle ID" + vehicleOwnership.getVehicleId() + " is not ava associated with owner ID" + vehicleOwnership.getOwnerId());
        }

    }

    @Override
    public void deleteVehicleOwnership(Long id) {
        Optional<VehicleOwnership> optionalVehicleOwnership = vehicleOwnershipRepository.findById(id);
        if (optionalVehicleOwnership.isPresent()) {
            vehicleOwnershipRepository.delete(optionalVehicleOwnership.get());
        } else {
            throw new ResourceNotFoundException("Record ID " + id + " not found");
        }
    }
}
