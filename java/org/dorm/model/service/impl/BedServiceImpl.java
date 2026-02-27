package org.dorm.model.service.impl;

import org.dorm.model.dao.BedDAO;
import org.dorm.model.dao.impl.BedDAOImpl;
import org.dorm.model.entity.Bed;
import org.dorm.model.service.BedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 床位服务实现类
 */
public class BedServiceImpl implements BedService {
    private static final Logger logger = LoggerFactory.getLogger(BedServiceImpl.class);
    private BedDAO bedDAO = new BedDAOImpl();

    @Override
    public List<Bed> getAllBeds() {
        return bedDAO.findAll();
    }

    @Override
    public Bed getBedById(String bedId) {
        return bedDAO.findByBedId(bedId);
    }

    @Override
    public List<Bed> getAvailableBeds() {
        return bedDAO.findAvailableBeds();
    }

    @Override
    public List<Bed> getBedsByDormId(String dormId) {
        return bedDAO.findByDormId(dormId);
    }

    @Override
    public boolean addBed(Bed bed) {
        try {
            // 检查床位ID是否已存在
            if (bedDAO.findByBedId(bed.getBedId()) != null) {
                logger.warn("床位ID已存在：{}", bed.getBedId());
                return false;
            }

            return bedDAO.addBed(bed);
        } catch (Exception e) {
            logger.error("添加床位失败", e);
            return false;
        }
    }

    @Override
    public boolean updateBed(Bed bed) {
        return bedDAO.updateBed(bed);
    }

    @Override
    public boolean deleteBed(String bedId) {
        return bedDAO.deleteBed(bedId);
    }
}