// ========================================
// 瀹胯垗绠＄悊绯荤粺 - 鍏ㄩ儴Java浠ｇ爜鍚堝苟鏂囦欢
// 鐢熸垚鏃堕棿: 2026骞?鏈?4鏃?
// 鍖呭惈鎵€鏈塉ava婧愪唬鐮佹枃浠?
// ========================================

// ========================================
// Main.java - 涓诲簲鐢ㄧ▼搴忕被
// ========================================
package org.dorm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 瀹胯垗绠＄悊绯荤粺涓诲簲鐢ㄧ▼搴忕被
 */
public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) {
        try {
            // 娴嬭瘯鏁版嵁搴撹繛鎺?
            if (!DatabaseUtil.testConnection()) {
                logger.error("鏁版嵁搴撹繛鎺ュけ璐ワ紝璇锋鏌ユ暟鎹簱閰嶇疆鍜孧ySQL鏈嶅姟");
                // 鏄剧ず閿欒瀵硅瘽妗?
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("鏁版嵁搴撹繛鎺ラ敊璇?);
                alert.setHeaderText("鏃犳硶杩炴帴鍒版暟鎹簱");
                alert.setContentText("璇锋鏌ワ細\n1. MySQL鏈嶅姟鏄惁鍚姩\n2. 鏁版嵁搴撻厤缃槸鍚︽纭甛n3. 鏁版嵁搴撴槸鍚﹀瓨鍦?);
                alert.showAndWait();
                System.exit(1);
                return;
            }

            // 鍔犺浇鐧诲綍鐣岄潰
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 800); // 璁剧疆鏇村ぇ鐨勭獥鍙ｅ昂瀵?

            primaryStage.setTitle("瀹胯垗绠＄悊绯荤粺 - 鐧诲綍");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true); // 鍏佽璋冩暣绐楀彛澶у皬
            primaryStage.setMinWidth(1000); // 璁剧疆鏈€灏忓搴?
            primaryStage.setMinHeight(700); // 璁剧疆鏈€灏忛珮搴?
            primaryStage.centerOnScreen(); // 绐楀彛灞呬腑鏄剧ず
            primaryStage.show();

            logger.info("瀹胯垗绠＄悊绯荤粺鍚姩鎴愬姛");
        } catch (IOException e) {
            logger.error("鍔犺浇鐧诲綍鐣岄潰澶辫触", e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// ========================================
// Accommodation.java - 浣忓淇℃伅瀹炰綋绫?
// ========================================
package org.dorm.model.entity;

import java.util.Date;

/**
 * 浣忓淇℃伅瀹炰綋绫?
 * 鐢ㄤ簬鏄剧ず瀛︾敓鐨勪綇瀹垮垎閰嶆儏鍐?
 */
class Accommodation {
    private String studentId;
    private String studentName;
    private String collegeName;
    private String className;
    private String dormitoryId;
    private String bedId;
    private Date checkinDate;
    private String status; // "宸插叆浣?, "寰呭垎閰?, "宸查€€瀹?

    public Accommodation() {}

    public Accommodation(String studentId, String studentName, String collegeName,
                        String className, String dormitoryId, String bedId,
                        Date checkinDate, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.collegeName = collegeName;
        this.className = className;
        this.dormitoryId = dormitoryId;
        this.bedId = bedId;
        this.checkinDate = checkinDate;
        this.status = status;
    }

    // Getter and Setter methods
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", dormitoryId='" + dormitoryId + '\'' +
                ", bedId='" + bedId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

========================================
AccommodationController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.Accommodation;
import org.dorm.model.service.AccommodationService;
import org.dorm.model.service.impl.AccommodationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 浣忓鍒嗛厤鎺у埗鍣?
 * 澶勭悊浣忓鍒嗛厤鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class AccommodationController {
    private static final Logger logger = LoggerFactory.getLogger(AccommodationController.class);
    private AccommodationService accommodationService = new AccommodationServiceImpl();

    /**
     * 鑾峰彇鎵€鏈変綇瀹夸俊鎭?
     * @return 浣忓淇℃伅鍒楄〃
     */
    public List<Accommodation> getAllAccommodations() {
        try {
            List<Accommodation> accommodations = accommodationService.getAllAccommodations();
            logger.info("鑾峰彇鎵€鏈変綇瀹夸俊鎭垚鍔燂細{}鏉¤褰?, accommodations.size());
            return accommodations;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈変綇瀹夸俊鎭け璐?, e);
            throw new RuntimeException("鑾峰彇浣忓淇℃伅澶辫触", e);
        }
    }

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇浣忓淇℃伅
     * @param studentId 瀛︾敓ID
     * @return 浣忓淇℃伅
     */
    public Accommodation getAccommodationByStudentId(String studentId) {
        try {
            Accommodation accommodation = accommodationService.getAccommodationByStudentId(studentId);
            if (accommodation != null) {
                logger.info("鑾峰彇瀛︾敓浣忓淇℃伅鎴愬姛锛歿}", studentId);
            } else {
                logger.warn("鏈壘鍒板鐢熶綇瀹夸俊鎭細{}", studentId);
            }
            return accommodation;
        } catch (Exception e) {
            logger.error("鑾峰彇瀛︾敓浣忓淇℃伅澶辫触锛歿}", studentId, e);
            throw new RuntimeException("鑾峰彇瀛︾敓浣忓淇℃伅澶辫触", e);
        }
    }

    /**
     * 鍒嗛厤搴婁綅
     * @param studentId 瀛︾敓ID
     * @param bedId 搴婁綅ID
     * @return 鏄惁鎴愬姛
     */
    public boolean assignBed(String studentId, String bedId) {
        try {
            boolean result = accommodationService.assignBed(studentId, bedId);
            if (result) {
                logger.info("鍒嗛厤搴婁綅鎴愬姛锛氬鐢焮} -> 搴婁綅{}", studentId, bedId);
            } else {
                logger.warn("鍒嗛厤搴婁綅澶辫触锛氬鐢焮} -> 搴婁綅{}", studentId, bedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒嗛厤搴婁綅寮傚父锛氬鐢焮} -> 搴婁綅{}", studentId, bedId, e);
            return false;
        }
    }

    /**
     * 璋冩崲搴婁綅
     * @param studentId 瀛︾敓ID
     * @param newBedId 鏂板簥浣岻D
     * @return 鏄惁鎴愬姛
     */
    public boolean changeBed(String studentId, String newBedId) {
        try {
            boolean result = accommodationService.changeBed(studentId, newBedId);
            if (result) {
                logger.info("璋冩崲搴婁綅鎴愬姛锛氬鐢焮} -> 鏂板簥浣峽}", studentId, newBedId);
            } else {
                logger.warn("璋冩崲搴婁綅澶辫触锛氬鐢焮} -> 鏂板簥浣峽}", studentId, newBedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("璋冩崲搴婁綅寮傚父锛氬鐢焮} -> 鏂板簥浣峽}", studentId, newBedId, e);
            return false;
        }
    }

    /**
     * 閫€瀹?
     * @param studentId 瀛︾敓ID
     * @return 鏄惁鎴愬姛
     */
    public boolean checkout(String studentId) {
        try {
            boolean result = accommodationService.checkout(studentId);
            if (result) {
                logger.info("閫€瀹挎垚鍔燂細瀛︾敓{}", studentId);
            } else {
                logger.warn("閫€瀹垮け璐ワ細瀛︾敓{}", studentId);
            }
            return result;
        } catch (Exception e) {
            logger.error("閫€瀹垮紓甯革細瀛︾敓{}", studentId, e);
            return false;
        }
    }

    /**
     * 鑾峰彇绌洪棽搴婁綅鍒楄〃
     * @return 绌洪棽搴婁綅ID鍒楄〃
     */
    public List<String> getAvailableBeds() {
        try {
            List<String> availableBeds = accommodationService.getAvailableBeds();
            logger.info("鑾峰彇绌洪棽搴婁綅鎴愬姛锛歿}涓?, availableBeds.size());
            return availableBeds;
        } catch (Exception e) {
            logger.error("鑾峰彇绌洪棽搴婁綅澶辫触", e);
            throw new RuntimeException("鑾峰彇绌洪棽搴婁綅澶辫触", e);
        }
    }

    /**
     * 鏍规嵁鏉′欢鎼滅储浣忓淇℃伅
     * @param searchText 鎼滅储鏂囨湰
     * @param searchType 鎼滅储绫诲瀷
     * @return 浣忓淇℃伅鍒楄〃
     */
    public List<Accommodation> searchAccommodations(String searchText, String searchType) {
        try {
            List<Accommodation> accommodations = accommodationService.searchAccommodations(searchText, searchType);
            logger.info("鎼滅储浣忓淇℃伅鎴愬姛锛岀被鍨嬶細{}锛屽叧閿瘝锛歿}锛岀粨鏋滐細{}鏉¤褰?,
                       searchType, searchText, accommodations.size());
            return accommodations;
        } catch (Exception e) {
            logger.error("鎼滅储浣忓淇℃伅寮傚父锛岀被鍨嬶細{}锛屽叧閿瘝锛歿}", searchType, searchText, e);
            throw new RuntimeException("鎼滅储浣忓淇℃伅澶辫触", e);
        }
    }
}

========================================
AnnouncementController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.Announcement;
import org.dorm.model.service.AnnouncementService;
import org.dorm.model.service.impl.AnnouncementServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 鍏憡鎺у埗鍣?
 * 澶勭悊鍏憡鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class AnnouncementController {
    private static final Logger logger = LoggerFactory.getLogger(AnnouncementController.class);
    private AnnouncementService announcementService = new AnnouncementServiceImpl();

    /**
     * 鑾峰彇鎵€鏈夋縺娲荤姸鎬佺殑鍏憡锛堝鐢熸煡鐪嬶級
     * @return 鍏憡鍒楄〃
     */
    public List<Announcement> getAllActiveAnnouncements() {
        try {
            return announcementService.getAllActiveAnnouncements();
        } catch (Exception e) {
            logger.error("鑾峰彇婵€娲诲叕鍛婂け璐?, e);
            throw new RuntimeException("鑾峰彇鍏憡澶辫触", e);
        }
    }

    /**
     * 鑾峰彇鎵€鏈夊叕鍛婏紙绠＄悊鍛樻煡鐪嬶級
     * @return 鍏憡鍒楄〃
     */
    public List<Announcement> getAllAnnouncements() {
        try {
            return announcementService.getAllAnnouncements();
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈夊叕鍛婂け璐?, e);
            throw new RuntimeException("鑾峰彇鍏憡澶辫触", e);
        }
    }

    /**
     * 鏍规嵁ID鑾峰彇鍏憡
     * @param announcementId 鍏憡ID
     * @return 鍏憡瀵硅薄
     */
    public Announcement getAnnouncementById(String announcementId) {
        try {
            return announcementService.getAnnouncementById(announcementId);
        } catch (Exception e) {
            logger.error("鑾峰彇鍏憡璇︽儏澶辫触锛歿}", announcementId, e);
            return null;
        }
    }

    /**
     * 鍙戝竷鏂板叕鍛?
     * @param announcement 鍏憡瀵硅薄
     * @return 鏄惁鎴愬姛
     */
    public boolean publishAnnouncement(Announcement announcement) {
        if (announcement == null) {
            logger.warn("鍙戝竷鍏憡澶辫触锛氬叕鍛婁俊鎭负绌?);
            return false;
        }

        try {
            boolean result = announcementService.publishAnnouncement(announcement);
            if (result) {
                logger.info("鍏憡鍙戝竷鎴愬姛锛歿}", announcement.getTitle());
            } else {
                logger.error("鍏憡鍙戝竷澶辫触锛歿}", announcement.getTitle());
            }
            return result;
        } catch (Exception e) {
            logger.error("鍙戝竷鍏憡杩囩▼涓彂鐢熷紓甯?, e);
            return false;
        }
    }

    /**
     * 鏇存柊鍏憡
     * @param announcement 鍏憡瀵硅薄
     * @return 鏄惁鎴愬姛
     */
    public boolean updateAnnouncement(Announcement announcement) {
        if (announcement == null) {
            logger.warn("鏇存柊鍏憡澶辫触锛氬叕鍛婁俊鎭负绌?);
            return false;
        }

        try {
            boolean result = announcementService.updateAnnouncement(announcement);
            if (result) {
                logger.info("鍏憡鏇存柊鎴愬姛锛歿}", announcement.getAnnouncementId());
            } else {
                logger.error("鍏憡鏇存柊澶辫触锛歿}", announcement.getAnnouncementId());
            }
            return result;
        } catch (Exception e) {
            logger.error("鏇存柊鍏憡杩囩▼涓彂鐢熷紓甯?, e);
            return false;
        }
    }

    /**
     * 鍒犻櫎鍏憡
     * @param announcementId 鍏憡ID
     * @return 鏄惁鎴愬姛
     */
    public boolean deleteAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("鍒犻櫎鍏憡澶辫触锛氬叕鍛奍D涓虹┖");
            return false;
        }

        try {
            boolean result = announcementService.deleteAnnouncement(announcementId.trim());
            if (result) {
                logger.info("鍏憡鍒犻櫎鎴愬姛锛歿}", announcementId);
            } else {
                logger.error("鍏憡鍒犻櫎澶辫触锛歿}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒犻櫎鍏憡杩囩▼涓彂鐢熷紓甯?, e);
            return false;
        }
    }

    /**
     * 婵€娲诲叕鍛?
     * @param announcementId 鍏憡ID
     * @return 鏄惁鎴愬姛
     */
    public boolean activateAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("婵€娲诲叕鍛婂け璐ワ細鍏憡ID涓虹┖");
            return false;
        }

        try {
            boolean result = announcementService.activateAnnouncement(announcementId.trim());
            if (result) {
                logger.info("鍏憡婵€娲绘垚鍔燂細{}", announcementId);
            } else {
                logger.error("鍏憡婵€娲诲け璐ワ細{}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("婵€娲诲叕鍛婅繃绋嬩腑鍙戠敓寮傚父", e);
            return false;
        }
    }

    /**
     * 鍋滅敤鍏憡
     * @param announcementId 鍏憡ID
     * @return 鏄惁鎴愬姛
     */
    public boolean deactivateAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("鍋滅敤鍏憡澶辫触锛氬叕鍛奍D涓虹┖");
            return false;
        }

        try {
            boolean result = announcementService.deactivateAnnouncement(announcementId.trim());
            if (result) {
                logger.info("鍏憡鍋滅敤鎴愬姛锛歿}", announcementId);
            } else {
                logger.error("鍏憡鍋滅敤澶辫触锛歿}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍋滅敤鍏憡杩囩▼涓彂鐢熷紓甯?, e);
            return false;
        }
    }

    /**
     * 鏍规嵁鍒嗙被鑾峰彇鍏憡
     * @param category 鍒嗙被
     * @return 鍏憡鍒楄〃
     */
    public List<Announcement> getAnnouncementsByCategory(String category) {
        try {
            return announcementService.getAnnouncementsByCategory(category);
        } catch (Exception e) {
            logger.error("鑾峰彇鍒嗙被鍏憡澶辫触锛歿}", category, e);
            throw new RuntimeException("鑾峰彇鍒嗙被鍏憡澶辫触", e);
        }
    }

    /**
     * 鑾峰彇鏈€鏂扮殑N鏉″叕鍛?
     * @param limit 鏁伴噺闄愬埗
     * @return 鍏憡鍒楄〃
     */
    public List<Announcement> getLatestAnnouncements(int limit) {
        try {
            return announcementService.getLatestAnnouncements(limit);
        } catch (Exception e) {
            logger.error("鑾峰彇鏈€鏂板叕鍛婂け璐?, e);
            throw new RuntimeException("鑾峰彇鏈€鏂板叕鍛婂け璐?, e);
        }
    }

    /**
     * 鑾峰彇閲嶈鍏憡锛堢揣鎬ラ€氱煡鍜岄噸瑕侀€氱煡锛?
     * @return 鍏憡鍒楄〃
     */
    public List<Announcement> getImportantAnnouncements() {
        try {
            return announcementService.getImportantAnnouncements();
        } catch (Exception e) {
            logger.error("鑾峰彇閲嶈鍏憡澶辫触", e);
            throw new RuntimeException("鑾峰彇閲嶈鍏憡澶辫触", e);
        }
    }

    /**
     * 鐢熸垚鍏憡ID
     * @return 鏂板叕鍛奍D
     */
    public String generateAnnouncementId() {
        return announcementService.generateAnnouncementId();
    }
}

========================================
BedController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.Bed;
import org.dorm.model.service.BedService;
import org.dorm.model.service.impl.BedServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 搴婁綅鎺у埗鍣?
 * 澶勭悊搴婁綅鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class BedController {
    private static final Logger logger = LoggerFactory.getLogger(BedController.class);

    private BedService bedService = new BedServiceImpl();

    /**
     * 鑾峰彇鎵€鏈夊簥浣?
     * @return 搴婁綅鍒楄〃
     */
    public List<Bed> getAllBeds() {
        try {
            List<Bed> beds = bedService.getAllBeds();
            logger.info("鑾峰彇鎵€鏈夊簥浣嶆垚鍔燂細{}涓?, beds.size());
            return beds;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈夊簥浣嶅け璐?, e);
            throw new RuntimeException("鑾峰彇搴婁綅鍒楄〃澶辫触", e);
        }
    }

    /**
     * 鏍规嵁搴婁綅ID鏌ユ壘搴婁綅
     * @param bedId 搴婁綅ID
     * @return 搴婁綅
     */
    public Bed getBedById(String bedId) {
        try {
            Bed bed = bedService.getBedById(bedId);
            if (bed != null) {
                logger.info("鏌ユ壘搴婁綅鎴愬姛锛歿}", bedId);
            } else {
                logger.warn("鏈壘鍒板簥浣嶏細{}", bedId);
            }
            return bed;
        } catch (Exception e) {
            logger.error("鏌ユ壘搴婁綅澶辫触锛歿}", bedId, e);
            throw new RuntimeException("鏌ユ壘搴婁綅澶辫触", e);
        }
    }

    /**
     * 鑾峰彇鍙敤搴婁綅
     * @return 鍙敤搴婁綅鍒楄〃
     */
    public List<Bed> getAvailableBeds() {
        try {
            List<Bed> beds = bedService.getAvailableBeds();
            logger.info("鑾峰彇鍙敤搴婁綅鎴愬姛锛歿}涓?, beds.size());
            return beds;
        } catch (Exception e) {
            logger.error("鑾峰彇鍙敤搴婁綅澶辫触", e);
            throw new RuntimeException("鑾峰彇鍙敤搴婁綅澶辫触", e);
        }
    }

    /**
     * 鏍规嵁瀹胯垗ID鑾峰彇搴婁綅
     * @param dormId 瀹胯垗ID
     * @return 搴婁綅鍒楄〃
     */
    public List<Bed> getBedsByDormId(String dormId) {
        try {
            List<Bed> beds = bedService.getBedsByDormId(dormId);
            logger.info("鑾峰彇瀹胯垗{}鐨勫簥浣嶆垚鍔燂細{}涓?, dormId, beds.size());
            return beds;
        } catch (Exception e) {
            logger.error("鑾峰彇瀹胯垗搴婁綅澶辫触锛歿}", dormId, e);
            throw new RuntimeException("鑾峰彇瀹胯垗搴婁綅澶辫触", e);
        }
    }

    /**
     * 娣诲姞搴婁綅
     * @param bed 搴婁綅
     * @return 鏄惁娣诲姞鎴愬姛
     */
    public boolean addBed(Bed bed) {
        try {
            boolean result = bedService.addBed(bed);
            if (result) {
                logger.info("娣诲姞搴婁綅鎴愬姛锛歿}", bed.getBedId());
            } else {
                logger.warn("娣诲姞搴婁綅澶辫触锛歿}", bed.getBedId());
            }
            return result;
        } catch (Exception e) {
            logger.error("娣诲姞搴婁綅澶辫触锛歿}", bed.getBedId(), e);
            return false;
        }
    }

    /**
     * 鏇存柊搴婁綅
     * @param bed 搴婁綅
     * @return 鏄惁鏇存柊鎴愬姛
     */
    public boolean updateBed(Bed bed) {
        try {
            boolean result = bedService.updateBed(bed);
            if (result) {
                logger.info("鏇存柊搴婁綅鎴愬姛锛歿}", bed.getBedId());
            } else {
                logger.warn("鏇存柊搴婁綅澶辫触锛歿}", bed.getBedId());
            }
            return result;
        } catch (Exception e) {
            logger.error("鏇存柊搴婁綅澶辫触锛歿}", bed.getBedId(), e);
            return false;
        }
    }

    /**
     * 鍒犻櫎搴婁綅
     * @param bedId 搴婁綅ID
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    public boolean deleteBed(String bedId) {
        try {
            boolean result = bedService.deleteBed(bedId);
            if (result) {
                logger.info("鍒犻櫎搴婁綅鎴愬姛锛歿}", bedId);
            } else {
                logger.warn("鍒犻櫎搴婁綅澶辫触锛歿}", bedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒犻櫎搴婁綅澶辫触锛歿}", bedId, e);
            return false;
        }
    }
}

========================================
DormitoryController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.Dormitory;
import org.dorm.model.service.DormitoryService;
import org.dorm.model.service.impl.DormitoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 瀹胯垗鎺у埗鍣?
 * 澶勭悊瀹胯垗鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class DormitoryController {
    private static final Logger logger = LoggerFactory.getLogger(DormitoryController.class);

    private DormitoryService dormitoryService = new DormitoryServiceImpl();

    /**
     * 鑾峰彇鎵€鏈夊鑸?
     * @return 瀹胯垗鍒楄〃
     */
    public List<Dormitory> getAllDormitories() {
        try {
            List<Dormitory> dormitories = dormitoryService.getAllDormitories();
            logger.info("鑾峰彇鎵€鏈夊鑸嶆垚鍔燂細{}鏍?, dormitories.size());
            return dormitories;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈夊鑸嶅け璐?, e);
            throw new RuntimeException("鑾峰彇瀹胯垗鍒楄〃澶辫触", e);
        }
    }

    /**
     * 鏍规嵁瀹胯垗ID鏌ユ壘瀹胯垗
     * @param dormitoryId 瀹胯垗ID
     * @return 瀹胯垗淇℃伅
     */
    public Dormitory getDormitoryById(String dormitoryId) {
        try {
            Dormitory dormitory = dormitoryService.getDormitoryById(dormitoryId);
            if (dormitory != null) {
                logger.info("鏌ユ壘瀹胯垗鎴愬姛锛歿}", dormitoryId);
            } else {
                logger.warn("鏈壘鍒板鑸嶏細{}", dormitoryId);
            }
            return dormitory;
        } catch (Exception e) {
            logger.error("鏌ユ壘瀹胯垗澶辫触锛歿}", dormitoryId, e);
            throw new RuntimeException("鏌ユ壘瀹胯垗澶辫触", e);
        }
    }

    /**
     * 娣诲姞瀹胯垗
     * @param dormitory 瀹胯垗淇℃伅
     * @return 鏄惁娣诲姞鎴愬姛
     */
    public boolean addDormitory(Dormitory dormitory) {
        try {
            boolean result = dormitoryService.addDormitory(dormitory);
            if (result) {
                logger.info("娣诲姞瀹胯垗鎴愬姛锛歿}", dormitory.getDormitoryId());
            } else {
                logger.warn("娣诲姞瀹胯垗澶辫触锛歿}", dormitory.getDormitoryId());
            }
            return result;
        } catch (Exception e) {
            logger.error("娣诲姞瀹胯垗澶辫触锛歿}", dormitory.getDormitoryId(), e);
            return false;
        }
    }

    /**
     * 鏇存柊瀹胯垗淇℃伅
     * @param dormitory 瀹胯垗淇℃伅
     * @return 鏄惁鏇存柊鎴愬姛
     */
    public boolean updateDormitory(Dormitory dormitory) {
        try {
            boolean result = dormitoryService.updateDormitory(dormitory);
            if (result) {
                logger.info("鏇存柊瀹胯垗鎴愬姛锛歿}", dormitory.getDormitoryId());
            } else {
                logger.warn("鏇存柊瀹胯垗澶辫触锛歿}", dormitory.getDormitoryId());
            }
            return result;
        } catch (Exception e) {
            logger.error("鏇存柊瀹胯垗澶辫触锛歿}", dormitory.getDormitoryId(), e);
            return false;
        }
    }

    /**
     * 鍒犻櫎瀹胯垗
     * @param dormitoryId 瀹胯垗ID
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    public boolean deleteDormitory(String dormitoryId) {
        try {
            boolean result = dormitoryService.deleteDormitory(dormitoryId);
            if (result) {
                logger.info("鍒犻櫎瀹胯垗鎴愬姛锛歿}", dormitoryId);
            } else {
                logger.warn("鍒犻櫎瀹胯垗澶辫触锛歿}", dormitoryId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒犻櫎瀹胯垗澶辫触锛歿}", dormitoryId, e);
            return false;
        }
    }
}

========================================
LoginController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.User;
import org.dorm.model.service.UserService;
import org.dorm.model.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 鐧诲綍鎺у埗鍣?
 * 澶勭悊鐢ㄦ埛鐧诲綍鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private UserService userService = new UserServiceImpl();

    /**
     * 鐢ㄦ埛鐧诲綍楠岃瘉
     * @param userId 鐢ㄦ埛鍚?
     * @param password 瀵嗙爜
     * @return 鐧诲綍缁撴灉锛歯ull琛ㄧず鐧诲綍澶辫触锛屽惁鍒欒繑鍥炵敤鎴峰璞?
     */
    public User login(String userId, String password) {
        if (userId == null || userId.trim().isEmpty()) {
            logger.warn("鐧诲綍澶辫触锛氱敤鎴峰悕涓虹┖");
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            logger.warn("鐧诲綍澶辫触锛氬瘑鐮佷负绌?);
            return null;
        }

        try {
            User user = userService.login(userId.trim(), password.trim());
            if (user != null) {
                logger.info("鐢ㄦ埛鐧诲綍鎴愬姛锛歿} ({})", userId, user.getUserType());
                return user;
            } else {
                logger.warn("鐢ㄦ埛鐧诲綍澶辫触锛氱敤鎴峰悕鎴栧瘑鐮侀敊璇?- {}", userId);
                return null;
            }
        } catch (Exception e) {
            logger.error("鐧诲綍杩囩▼涓彂鐢熷紓甯革細{}", userId, e);
            return null;
        }
    }

    /**
     * 妫€鏌ョ敤鎴峰悕鏄惁瀛樺湪
     * @param userId 鐢ㄦ埛鍚?
     * @return 鏄惁瀛樺湪
     */
    public boolean isUserExists(String userId) {
        return userService.isUserExists(userId);
    }

    /**
     * 淇敼瀵嗙爜
     * @param userId 鐢ㄦ埛鍚?
     * @param oldPassword 鏃у瘑鐮?
     * @param newPassword 鏂板瘑鐮?
     * @return 鏄惁淇敼鎴愬姛
     */
    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        try {
            boolean result = userService.changePassword(userId, oldPassword, newPassword);
            if (result) {
                logger.info("瀵嗙爜淇敼鎴愬姛锛歿}", userId);
            } else {
                logger.warn("瀵嗙爜淇敼澶辫触锛歿}", userId);
            }
            return result;
        } catch (Exception e) {
            logger.error("淇敼瀵嗙爜杩囩▼涓彂鐢熷紓甯革細{}", userId, e);
            return false;
        }
    }
}

========================================
PaymentController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.Payment;
import org.dorm.model.service.PaymentService;
import org.dorm.model.service.impl.PaymentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 缂磋垂鎺у埗鍣?
 * 澶勭悊缂磋垂鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private PaymentService paymentService = new PaymentServiceImpl();

    /**
     * 鑾峰彇鎵€鏈夌即璐硅褰?
     * @return 缂磋垂璁板綍鍒楄〃
     */
    public List<Payment> getAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            logger.info("鑾峰彇鎵€鏈夌即璐硅褰曟垚鍔燂細{}鏉?, payments.size());
            return payments;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈夌即璐硅褰曞け璐?, e);
            throw new RuntimeException("鑾峰彇缂磋垂璁板綍鍒楄〃澶辫触", e);
        }
    }

    /**
     * 鏍规嵁缂磋垂鍗曞彿鏌ユ壘缂磋垂璁板綍
     * @param paymentId 缂磋垂鍗曞彿
     * @return 缂磋垂璁板綍
     */
    public Payment getPaymentById(String paymentId) {
        try {
            Payment payment = paymentService.getPaymentById(paymentId);
            if (payment != null) {
                logger.info("鏌ユ壘缂磋垂璁板綍鎴愬姛锛歿}", paymentId);
            } else {
                logger.warn("鏈壘鍒扮即璐硅褰曪細{}", paymentId);
            }
            return payment;
        } catch (Exception e) {
            logger.error("鏌ユ壘缂磋垂璁板綍澶辫触锛歿}", paymentId, e);
            throw new RuntimeException("鏌ユ壘缂磋垂璁板綍澶辫触", e);
        }
    }

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇缂磋垂璁板綍
     * @param studentId 瀛︾敓ID
     * @return 缂磋垂璁板綍鍒楄〃
     */
    public List<Payment> getPaymentsByStudentId(String studentId) {
        try {
            List<Payment> payments = paymentService.getPaymentsByStudentId(studentId);
            logger.info("鑾峰彇瀛︾敓{}鐨勭即璐硅褰曟垚鍔燂細{}鏉?, studentId, payments.size());
            return payments;
        } catch (Exception e) {
            logger.error("鑾峰彇瀛︾敓缂磋垂璁板綍澶辫触锛歿}", studentId, e);
            throw new RuntimeException("鑾峰彇瀛︾敓缂磋垂璁板綍澶辫触", e);
        }
    }

    /**
     * 娣诲姞缂磋垂璁板綍
     * @param payment 缂磋垂璁板綍
     * @return 鏄惁娣诲姞鎴愬姛
     */
    public boolean addPayment(Payment payment) {
        try {
            boolean result = paymentService.addPayment(payment);
            if (result) {
                logger.info("娣诲姞缂磋垂璁板綍鎴愬姛锛歿}", payment.getPaymentId());
            } else {
                logger.warn("娣诲姞缂磋垂璁板綍澶辫触锛歿}", payment.getPaymentId());
            }
            return result;
        } catch (Exception e) {
            logger.error("娣诲姞缂磋垂璁板綍澶辫触锛歿}", payment.getPaymentId(), e);
            return false;
        }
    }

    /**
     * 鏇存柊缂磋垂璁板綍
     * @param payment 缂磋垂璁板綍
     * @return 鏄惁鏇存柊鎴愬姛
     */
    public boolean updatePayment(Payment payment) {
        try {
            boolean result = paymentService.updatePayment(payment);
            if (result) {
                logger.info("鏇存柊缂磋垂璁板綍鎴愬姛锛歿}", payment.getPaymentId());
            } else {
                logger.warn("鏇存柊缂磋垂璁板綍澶辫触锛歿}", payment.getPaymentId());
            }
            return result;
        } catch (Exception e) {
            logger.error("鏇存柊缂磋垂璁板綍澶辫触锛歿}", payment.getPaymentId(), e);
            return false;
        }
    }

    /**
     * 鍒犻櫎缂磋垂璁板綍
     * @param paymentId 缂磋垂鍗曞彿
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    public boolean deletePayment(String paymentId) {
        try {
            boolean result = paymentService.deletePayment(paymentId);
            if (result) {
                logger.info("鍒犻櫎缂磋垂璁板綍鎴愬姛锛歿}", paymentId);
            } else {
                logger.warn("鍒犻櫎缂磋垂璁板綍澶辫触锛歿}", paymentId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒犻櫎缂磋垂璁板綍澶辫触锛歿}", paymentId, e);
            return false;
        }
    }
}

========================================
ReminderController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.Reminder;
import org.dorm.model.service.ReminderService;
import org.dorm.model.service.impl.ReminderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 鎻愰啋鎺у埗鍣? * 澶勭悊鎻愰啋鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class ReminderController {
    private static final Logger logger = LoggerFactory.getLogger(ReminderController.class);

    private ReminderService reminderService = new ReminderServiceImpl();

    /**
     * 鑾峰彇鎵€鏈夋彁閱?     * @return 鎻愰啋鍒楄〃
     */
    public List<Reminder> getAllReminders() {
        try {
            List<Reminder> reminders = reminderService.getAllReminders();
            logger.info("鑾峰彇鎵€鏈夋彁閱掓垚鍔燂細{}鏉?, reminders.size());
            return reminders;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈夋彁閱掑け璐?, e);
            throw new RuntimeException("鑾峰彇鎻愰啋鍒楄〃澶辫触", e);
        }
    }

    /**
     * 鏍规嵁鎻愰啋ID鏌ユ壘鎻愰啋
     * @param reminderId 鎻愰啋ID
     * @return 鎻愰啋淇℃伅
     */
    public Reminder getReminderById(String reminderId) {
        try {
            Reminder reminder = reminderService.getReminderById(reminderId);
            if (reminder != null) {
                logger.info("鏌ユ壘鎻愰啋鎴愬姛锛歿}", reminderId);
            } else {
                logger.warn("鏈壘鍒版彁閱掞細{}", reminderId);
            }
            return reminder;
        } catch (Exception e) {
            logger.error("鏌ユ壘鎻愰啋澶辫触锛歿}", reminderId, e);
            throw new RuntimeException("鏌ユ壘鎻愰啋澶辫触", e);
        }
    }

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇鎻愰啋鍒楄〃
     * @param studentId 瀛︾敓ID
     * @return 鎻愰啋鍒楄〃
     */
    public List<Reminder> getRemindersByStudentId(String studentId) {
        try {
            List<Reminder> reminders = reminderService.getRemindersByStudentId(studentId);
            logger.info("鑾峰彇瀛︾敓{}鐨勬彁閱掓垚鍔燂細{}鏉?, studentId, reminders.size());
            return reminders;
        } catch (Exception e) {
            logger.error("鑾峰彇瀛︾敓鎻愰啋澶辫触锛歿}", studentId, e);
            throw new RuntimeException("鑾峰彇瀛︾敓鎻愰啋澶辫触", e);
        }
    }

    /**
     * 鑾峰彇鏈鐞嗙殑鎻愰啋
     * @return 鏈鐞嗘彁閱掑垪琛?     */
    public List<Reminder> getUnprocessedReminders() {
        try {
            List<Reminder> reminders = reminderService.getUnprocessedReminders();
            logger.info("鑾峰彇鏈鐞嗘彁閱掓垚鍔燂細{}鏉?, reminders.size());
            return reminders;
        } catch (Exception e) {
            logger.error("鑾峰彇鏈鐞嗘彁閱掑け璐?, e);
            throw new RuntimeException("鑾峰彇鏈鐞嗘彁閱掑け璐?, e);
        }
    }

    /**
     * 娣诲姞鎻愰啋
     * @param reminder 鎻愰啋淇℃伅
     * @return 鏄惁娣诲姞鎴愬姛
     */
    public boolean addReminder(Reminder reminder) {
        try {
            boolean result = reminderService.addReminder(reminder);
            if (result) {
                logger.info("娣诲姞鎻愰啋鎴愬姛锛歿}", reminder.getReminderId());
            } else {
                logger.warn("娣诲姞鎻愰啋澶辫触锛歿}", reminder.getReminderId());
            }
            return result;
        } catch (Exception e) {
            logger.error("娣诲姞鎻愰啋澶辫触锛歿}", reminder.getReminderId(), e);
            return false;
        }
    }

    /**
     * 鏇存柊鎻愰啋
     * @param reminder 鎻愰啋淇℃伅
     * @return 鏄惁鏇存柊鎴愬姛
     */
    public boolean updateReminder(Reminder reminder) {
        try {
            boolean result = reminderService.updateReminder(reminder);
            if (result) {
                logger.info("鏇存柊鎻愰啋鎴愬姛锛歿}", reminder.getReminderId());
            } else {
                logger.warn("鏇存柊鎻愰啋澶辫触锛歿}", reminder.getReminderId());
            }
            return result;
        } catch (Exception e) {
            logger.error("鏇存柊鎻愰啋澶辫触锛歿}", reminder.getReminderId(), e);
            return false;
        }
    }

    /**
     * 鍒犻櫎鎻愰啋
     * @param reminderId 鎻愰啋ID
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    public boolean deleteReminder(String reminderId) {
        try {
            boolean result = reminderService.deleteReminder(reminderId);
            if (result) {
                logger.info("鍒犻櫎鎻愰啋鎴愬姛锛歿}", reminderId);
            } else {
                logger.warn("鍒犻櫎鎻愰啋澶辫触锛歿}", reminderId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒犻櫎鎻愰啋澶辫触锛歿}", reminderId, e);
            return false;
        }
    }

    /**
     * 妫€鏌ユ湭缂磋垂瀛︾敓骞剁敓鎴愭彁閱?     * @return 鐢熸垚鐨勬彁閱掓暟閲?     */
    public int checkUnpaidStudents() {
        try {
            int count = reminderService.checkUnpaidStudents();
            logger.info("鏈即璐瑰鐢熸鏌ュ畬鎴愶紝鐢熸垚浜唟}鏉℃彁閱?, count);
            return count;
        } catch (Exception e) {
            logger.error("妫€鏌ユ湭缂磋垂瀛︾敓澶辫触", e);
            return 0;
        }
    }

    /**
     * 妫€鏌ユ湭澶勭悊杩濈邯璁板綍骞剁敓鎴愭彁閱?     * @return 鐢熸垚鐨勬彁閱掓暟閲?     */
    public int checkUnprocessedViolations() {
        try {
            int count = reminderService.checkUnprocessedViolations();
            logger.info("鏈鐞嗚繚绾鏌ュ畬鎴愶紝鐢熸垚浜唟}鏉℃彁閱?, count);
            return count;
        } catch (Exception e) {
            logger.error("妫€鏌ユ湭澶勭悊杩濈邯璁板綍澶辫触", e);
            return 0;
        }
    }

    /**
     * 鎵归噺鍒犻櫎宸插鐞嗙殑鎻愰啋
     * @return 鍒犻櫎鐨勮褰曟暟
     */
    public int deleteProcessedReminders() {
        try {
            int count = reminderService.deleteProcessedReminders();
            logger.info("鍒犻櫎浜唟}鏉″凡澶勭悊鐨勬彁閱?, count);
            return count;
        } catch (Exception e) {
            logger.error("鍒犻櫎宸插鐞嗘彁閱掑け璐?, e);
            return 0;
        }
    }

    /**
     * 鍒锋柊鎵€鏈夋彁閱?     * @return 鏇存柊鐨勬彁閱掓€绘暟
     */
    public int refreshAllReminders() {
        try {
            int count = reminderService.refreshAllReminders();
            logger.info("鎻愰啋鍒锋柊瀹屾垚锛屽叡{}鏉℃彁閱?, count);
            return count;
        } catch (Exception e) {
            logger.error("鍒锋柊鎻愰啋澶辫触", e);
            return 0;
        }
    }
}

========================================
RepairApplicationController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.RepairApplication;
import org.dorm.model.service.RepairApplicationService;
import org.dorm.model.service.impl.RepairApplicationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 缁翠慨鐢宠鎺у埗鍣?
 * 澶勭悊缁翠慨鐢宠鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class RepairApplicationController {
    private static final Logger logger = LoggerFactory.getLogger(RepairApplicationController.class);
    private RepairApplicationService repairService = new RepairApplicationServiceImpl();

    /**
     * 鎻愪氦缁翠慨鐢宠
     * @param application 缁翠慨鐢宠瀵硅薄
     * @return 鏄惁鎻愪氦鎴愬姛
     */
    public boolean submitRepairApplication(RepairApplication application) {
        if (application == null) {
            logger.warn("鎻愪氦缁翠慨鐢宠澶辫触锛氱敵璇蜂俊鎭负绌?);
            return false;
        }

        try {
            boolean result = repairService.submitRepairApplication(application);
            if (result) {
                logger.info("缁翠慨鐢宠鎻愪氦鎴愬姛锛歿}", application.getApplyId());
            } else {
                logger.error("缁翠慨鐢宠鎻愪氦澶辫触");
            }
            return result;
        } catch (Exception e) {
            logger.error("鎻愪氦缁翠慨鐢宠杩囩▼涓彂鐢熷紓甯?, e);
            return false;
        }
    }

    /**
     * 鑾峰彇瀛︾敓鑷繁鐨勭淮淇敵璇疯褰?
     * @param studentId 瀛︾敓瀛﹀彿
     * @return 缁翠慨鐢宠鍒楄〃
     */
    public List<RepairApplication> getStudentApplications(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            logger.warn("鑾峰彇瀛︾敓缁翠慨鐢宠澶辫触锛氬鐢熷鍙蜂负绌?);
            return List.of();
        }

        try {
            List<RepairApplication> applications = repairService.getStudentApplications(studentId.trim());
            logger.info("鑾峰彇瀛︾敓缁翠慨鐢宠鎴愬姛锛歿} - {}鏉¤褰?, studentId, applications.size());
            return applications;
        } catch (Exception e) {
            logger.error("鑾峰彇瀛︾敓缁翠慨鐢宠杩囩▼涓彂鐢熷紓甯革細{}", studentId, e);
            return List.of();
        }
    }

    /**
     * 鑾峰彇鎵€鏈夌淮淇敵璇凤紙绠＄悊鍛樺姛鑳斤級
     * @return 缁翠慨鐢宠鍒楄〃
     */
    public List<RepairApplication> getAllApplications() {
        try {
            List<RepairApplication> applications = repairService.getAllApplications();
            logger.info("鑾峰彇鎵€鏈夌淮淇敵璇锋垚鍔燂細{}鏉¤褰?, applications.size());
            return applications;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈夌淮淇敵璇疯繃绋嬩腑鍙戠敓寮傚父", e);
            return List.of();
        }
    }

    /**
     * 鑾峰彇寰呭鐞嗙殑缁翠慨鐢宠
     * @return 缁翠慨鐢宠鍒楄〃
     */
    public List<RepairApplication> getPendingApplications() {
        try {
            List<RepairApplication> applications = repairService.getPendingApplications();
            logger.info("鑾峰彇寰呭鐞嗙淮淇敵璇锋垚鍔燂細{}鏉¤褰?, applications.size());
            return applications;
        } catch (Exception e) {
            logger.error("鑾峰彇寰呭鐞嗙淮淇敵璇疯繃绋嬩腑鍙戠敓寮傚父", e);
            return List.of();
        }
    }

    /**
     * 鏇存柊缁翠慨鐢宠鐘舵€侊紙绠＄悊鍛樺姛鑳斤級
     * @param applyId 鐢宠鍗曞彿
     * @param status 鏂扮姸鎬?
     * @param handler 澶勭悊浜?
     * @return 鏄惁鏇存柊鎴愬姛
     */
    public boolean updateApplicationStatus(String applyId, String status, String handler) {
        if (applyId == null || status == null) {
            logger.warn("鏇存柊缁翠慨鐢宠鐘舵€佸け璐ワ細鍙傛暟涓嶅畬鏁?);
            return false;
        }

        try {
            boolean result = repairService.updateApplicationStatus(applyId.trim(), status, handler);
            if (result) {
                logger.info("缁翠慨鐢宠鐘舵€佹洿鏂版垚鍔燂細{} -> {}", applyId, status);
            } else {
                logger.error("缁翠慨鐢宠鐘舵€佹洿鏂板け璐ワ細{}", applyId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鏇存柊缁翠慨鐢宠鐘舵€佽繃绋嬩腑鍙戠敓寮傚父锛歿}", applyId, e);
            return false;
        }
    }

    /**
     * 瀹屾垚缁翠慨鐢宠锛堢鐞嗗憳鍔熻兘锛?
     * @param applyId 鐢宠鍗曞彿
     * @return 鏄惁瀹屾垚鎴愬姛
     */
    public boolean completeRepairApplication(String applyId) {
        if (applyId == null || applyId.trim().isEmpty()) {
            logger.warn("瀹屾垚缁翠慨鐢宠澶辫触锛氱敵璇峰崟鍙蜂负绌?);
            return false;
        }

        try {
            boolean result = repairService.completeRepairApplication(applyId.trim());
            if (result) {
                logger.info("缁翠慨鐢宠瀹屾垚鎴愬姛锛歿}", applyId);
            } else {
                logger.error("缁翠慨鐢宠瀹屾垚澶辫触锛歿}", applyId);
            }
            return result;
        } catch (Exception e) {
            logger.error("瀹屾垚缁翠慨鐢宠杩囩▼涓彂鐢熷紓甯革細{}", applyId, e);
            return false;
        }
    }

    /**
     * 鐢熸垚鐢宠鍗曞彿
     * @param studentId 瀛︾敓瀛﹀彿
     * @return 鐢宠鍗曞彿
     */
    public String generateApplyId(String studentId) {
        return repairService.generateApplyId(studentId);
    }
}

========================================
StudentController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.Student;
import org.dorm.model.service.StudentService;
import org.dorm.model.service.impl.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 瀛︾敓鎺у埗鍣?
 * 澶勭悊瀛︾敓鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public StudentController() {
        logger.info("StudentController鏋勯€犲嚱鏁板紑濮嬫墽琛?);
        try {
            this.studentService = new StudentServiceImpl();
            logger.info("StudentService鍒濆鍖栨垚鍔?);
        } catch (Exception e) {
            logger.error("StudentService鍒濆鍖栧け璐?, e);
            throw e;
        }
    }

    private StudentService studentService;

    /**
     * 鑾峰彇鎵€鏈夊鐢?
     * @return 瀛︾敓鍒楄〃
     */
    public List<Student> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            logger.info("鑾峰彇鎵€鏈夊鐢熸垚鍔燂細{}浜?, students.size());
            return students;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈夊鐢熷け璐?, e);
            throw new RuntimeException("鑾峰彇瀛︾敓鍒楄〃澶辫触", e);
        }
    }

    /**
     * 鏍规嵁瀛﹀彿鏌ユ壘瀛︾敓
     * @param studentId 瀛﹀彿
     * @return 瀛︾敓淇℃伅
     */
    public Student getStudentById(String studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            if (student != null) {
                logger.info("鏌ユ壘瀛︾敓鎴愬姛锛歿}", studentId);
            } else {
                logger.warn("鏈壘鍒板鐢燂細{}", studentId);
            }
            return student;
        } catch (Exception e) {
            logger.error("鏌ユ壘瀛︾敓澶辫触锛歿}", studentId, e);
            throw new RuntimeException("鏌ユ壘瀛︾敓澶辫触", e);
        }
    }

    /**
     * 娣诲姞鏂板鐢?
     * @param student 瀛︾敓淇℃伅
     * @return 鏄惁鎴愬姛
     */
    public boolean addStudent(Student student) {
        try {
            boolean result = studentService.addStudent(student);
            if (result) {
                logger.info("娣诲姞瀛︾敓鎴愬姛锛歿}", student.getStudentId());
            } else {
                logger.warn("娣诲姞瀛︾敓澶辫触锛歿}", student.getStudentId());
            }
            return result;
        } catch (Exception e) {
            logger.error("娣诲姞瀛︾敓寮傚父锛歿}", student.getStudentId(), e);
            throw new RuntimeException("娣诲姞瀛︾敓澶辫触", e);
        }
    }

    /**
     * 鏇存柊瀛︾敓淇℃伅
     * @param student 瀛︾敓淇℃伅
     * @return 鏄惁鎴愬姛
     */
    public boolean updateStudent(Student student) {
        try {
            boolean result = studentService.updateStudent(student);
            if (result) {
                logger.info("鏇存柊瀛︾敓鎴愬姛锛歿}", student.getStudentId());
            } else {
                logger.warn("鏇存柊瀛︾敓澶辫触锛歿}", student.getStudentId());
            }
            return result;
        } catch (Exception e) {
            logger.error("鏇存柊瀛︾敓寮傚父锛歿}", student.getStudentId(), e);
            throw new RuntimeException("鏇存柊瀛︾敓澶辫触", e);
        }
    }

    /**
     * 鍒犻櫎瀛︾敓
     * @param studentId 瀛﹀彿
     * @return 鏄惁鎴愬姛
     */
    public boolean deleteStudent(String studentId) {
        try {
            boolean result = studentService.deleteStudent(studentId);
            if (result) {
                logger.info("鍒犻櫎瀛︾敓鎴愬姛锛歿}", studentId);
            } else {
                logger.warn("鍒犻櫎瀛︾敓澶辫触锛歿}", studentId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒犻櫎瀛︾敓寮傚父锛歿}", studentId, e);
            throw new RuntimeException("鍒犻櫎瀛︾敓澶辫触", e);
        }
    }

    /**
     * 鏍规嵁濮撳悕鎼滅储瀛︾敓
     * @param name 瀛︾敓濮撳悕
     * @return 瀛︾敓鍒楄〃
     */
    public List<Student> searchStudentsByName(String name) {
        try {
            List<Student> students = studentService.searchStudentsByName(name);
            logger.info("鎼滅储瀛︾敓鎴愬姛锛屽鍚嶏細{}锛岀粨鏋滐細{}浜?, name, students.size());
            return students;
        } catch (Exception e) {
            logger.error("鎼滅储瀛︾敓寮傚父锛屽鍚嶏細{}", name, e);
            throw new RuntimeException("鎼滅储瀛︾敓澶辫触", e);
        }
    }

    /**
     * 鑾峰彇瀛︾敓鎬绘暟
     * @return 瀛︾敓鏁伴噺
     */
    public int getStudentCount() {
        try {
            int count = studentService.getStudentCount();
            logger.info("鑾峰彇瀛︾敓鎬绘暟锛歿}", count);
            return count;
        } catch (Exception e) {
            logger.error("鑾峰彇瀛︾敓鎬绘暟寮傚父", e);
            throw new RuntimeException("鑾峰彇瀛︾敓鎬绘暟澶辫触", e);
        }
    }
}

========================================
UserController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.User;
import org.dorm.model.service.UserService;
import org.dorm.model.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 鐢ㄦ埛鎺у埗鍣?
 * 澶勭悊鐢ㄦ埛鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService = new UserServiceImpl();

    /**
     * 娣诲姞鏂扮敤鎴凤紙娉ㄥ唽锛?
     * @param user 鐢ㄦ埛淇℃伅
     * @return 鏄惁鎴愬姛
     */
    public boolean addUser(User user) {
        try {
            boolean result = userService.register(user);
            if (result) {
                logger.info("鐢ㄦ埛娉ㄥ唽鎴愬姛锛歿}", user.getUserId());
            } else {
                logger.warn("鐢ㄦ埛娉ㄥ唽澶辫触锛歿}", user.getUserId());
            }
            return result;
        } catch (Exception e) {
            logger.error("鐢ㄦ埛娉ㄥ唽寮傚父锛歿}", user.getUserId(), e);
            throw new RuntimeException("鐢ㄦ埛娉ㄥ唽澶辫触", e);
        }
    }


    /**
     * 鏍规嵁鐢ㄦ埛ID鏌ユ壘鐢ㄦ埛
     * @param userId 鐢ㄦ埛ID
     * @return 鐢ㄦ埛淇℃伅
     */
    public User getUserById(String userId) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                logger.info("鏌ユ壘鐢ㄦ埛鎴愬姛锛歿}", userId);
            } else {
                logger.warn("鏈壘鍒扮敤鎴凤細{}", userId);
            }
            return user;
        } catch (Exception e) {
            logger.error("鏌ユ壘鐢ㄦ埛寮傚父锛歿}", userId, e);
            throw new RuntimeException("鏌ユ壘鐢ㄦ埛澶辫触", e);
        }
    }
}

========================================
ViolationController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\controller
========================================
package org.dorm.controller;

import org.dorm.model.entity.Violation;
import org.dorm.model.service.ViolationService;
import org.dorm.model.service.impl.ViolationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 杩濈邯鎺у埗鍣?
 * 澶勭悊杩濈邯鐩稿叧鐨勪笟鍔￠€昏緫
 */
public class ViolationController {
    private static final Logger logger = LoggerFactory.getLogger(ViolationController.class);

    private ViolationService violationService = new ViolationServiceImpl();

    /**
     * 鑾峰彇鎵€鏈夎繚绾褰?
     * @return 杩濈邯璁板綍鍒楄〃
     */
    public List<Violation> getAllViolations() {
        try {
            List<Violation> violations = violationService.getAllViolations();
            logger.info("鑾峰彇鎵€鏈夎繚绾褰曟垚鍔燂細{}鏉?, violations.size());
            return violations;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈夎繚绾褰曞け璐?, e);
            throw new RuntimeException("鑾峰彇杩濈邯璁板綍鍒楄〃澶辫触", e);
        }
    }

    /**
     * 鏍规嵁杩濈邯鍗曞彿鏌ユ壘杩濈邯璁板綍
     * @param violationId 杩濈邯鍗曞彿
     * @return 杩濈邯璁板綍
     */
    public Violation getViolationById(String violationId) {
        try {
            Violation violation = violationService.getViolationById(violationId);
            if (violation != null) {
                logger.info("鏌ユ壘杩濈邯璁板綍鎴愬姛锛歿}", violationId);
            } else {
                logger.warn("鏈壘鍒拌繚绾褰曪細{}", violationId);
            }
            return violation;
        } catch (Exception e) {
            logger.error("鏌ユ壘杩濈邯璁板綍澶辫触锛歿}", violationId, e);
            throw new RuntimeException("鏌ユ壘杩濈邯璁板綍澶辫触", e);
        }
    }

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇杩濈邯璁板綍
     * @param studentId 瀛︾敓ID
     * @return 杩濈邯璁板綍鍒楄〃
     */
    public List<Violation> getViolationsByStudentId(String studentId) {
        try {
            List<Violation> violations = violationService.getViolationsByStudentId(studentId);
            logger.info("鑾峰彇瀛︾敓{}鐨勮繚绾褰曟垚鍔燂細{}鏉?, studentId, violations.size());
            return violations;
        } catch (Exception e) {
            logger.error("鑾峰彇瀛︾敓杩濈邯璁板綍澶辫触锛歿}", studentId, e);
            throw new RuntimeException("鑾峰彇瀛︾敓杩濈邯璁板綍澶辫触", e);
        }
    }

    /**
     * 娣诲姞杩濈邯璁板綍
     * @param violation 杩濈邯璁板綍
     * @return 鏄惁娣诲姞鎴愬姛
     */
    public boolean addViolation(Violation violation) {
        try {
            boolean result = violationService.addViolation(violation);
            if (result) {
                logger.info("娣诲姞杩濈邯璁板綍鎴愬姛锛歿}", violation.getViolationId());
            } else {
                logger.warn("娣诲姞杩濈邯璁板綍澶辫触锛歿}", violation.getViolationId());
            }
            return result;
        } catch (Exception e) {
            logger.error("娣诲姞杩濈邯璁板綍澶辫触锛歿}", violation.getViolationId(), e);
            return false;
        }
    }

    /**
     * 鏇存柊杩濈邯璁板綍
     * @param violation 杩濈邯璁板綍
     * @return 鏄惁鏇存柊鎴愬姛
     */
    public boolean updateViolation(Violation violation) {
        try {
            boolean result = violationService.updateViolation(violation);
            if (result) {
                logger.info("鏇存柊杩濈邯璁板綍鎴愬姛锛歿}", violation.getViolationId());
            } else {
                logger.warn("鏇存柊杩濈邯璁板綍澶辫触锛歿}", violation.getViolationId());
            }
            return result;
        } catch (Exception e) {
            logger.error("鏇存柊杩濈邯璁板綍澶辫触锛歿}", violation.getViolationId(), e);
            return false;
        }
    }

    /**
     * 鍒犻櫎杩濈邯璁板綍
     * @param violationId 杩濈邯鍗曞彿
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    public boolean deleteViolation(String violationId) {
        try {
            boolean result = violationService.deleteViolation(violationId);
            if (result) {
                logger.info("鍒犻櫎杩濈邯璁板綍鎴愬姛锛歿}", violationId);
            } else {
                logger.warn("鍒犻櫎杩濈邯璁板綍澶辫触锛歿}", violationId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒犻櫎杩濈邯璁板綍澶辫触锛歿}", violationId, e);
            return false;
        }
    }
}

========================================
AccommodationDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.AccommodationDAO;
import org.dorm.model.entity.Accommodation;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 浣忓鍒嗛厤鏁版嵁璁块棶瀹炵幇
 */
public class AccommodationDAOImpl implements AccommodationDAO {
    private static final Logger logger = LoggerFactory.getLogger(AccommodationDAOImpl.class);

    @Override
    public List<Accommodation> findAll() {
        String sql = "SELECT s.student_id, s.name, s.college_name, s.class_name, " +
                    "b.dorm_id, b.bed_id, s.bed_id IS NOT NULL as has_bed, " +
                    "NOW() as checkin_date " +
                    "FROM student s LEFT JOIN bed b ON s.bed_id = b.bed_id " +
                    "ORDER BY s.student_id";

        List<Accommodation> accommodations = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Accommodation accommodation = new Accommodation();
                accommodation.setStudentId(rs.getString("student_id"));
                accommodation.setStudentName(rs.getString("name"));
                accommodation.setCollegeName(rs.getString("college_name"));
                accommodation.setClassName(rs.getString("class_name"));
                accommodation.setDormitoryId(rs.getString("dorm_id"));
                accommodation.setBedId(rs.getString("bed_id"));
                accommodation.setCheckinDate(rs.getDate("checkin_date"));

                // 鏍规嵁鏄惁鏈夊簥浣嶈缃姸鎬?
                if (rs.getString("bed_id") != null) {
                    accommodation.setStatus("宸插叆浣?);
                } else {
                    accommodation.setStatus("寰呭垎閰?);
                }

                accommodations.add(accommodation);
            }

            logger.info("鑾峰彇浣忓淇℃伅鎴愬姛锛歿}鏉¤褰?, accommodations.size());

        } catch (SQLException e) {
            logger.error("鑾峰彇浣忓淇℃伅澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return accommodations;
    }

    @Override
    public Accommodation findByStudentId(String studentId) {
        String sql = "SELECT s.student_id, s.name, s.college_name, s.class_name, " +
                    "b.dorm_id, b.bed_id, NOW() as checkin_date " +
                    "FROM student s LEFT JOIN bed b ON s.bed_id = b.bed_id " +
                    "WHERE s.student_id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Accommodation accommodation = new Accommodation();
                accommodation.setStudentId(rs.getString("student_id"));
                accommodation.setStudentName(rs.getString("name"));
                accommodation.setCollegeName(rs.getString("college_name"));
                accommodation.setClassName(rs.getString("class_name"));
                accommodation.setDormitoryId(rs.getString("dorm_id"));
                accommodation.setBedId(rs.getString("bed_id"));
                accommodation.setCheckinDate(rs.getDate("checkin_date"));

                if (rs.getString("bed_id") != null) {
                    accommodation.setStatus("宸插叆浣?);
                } else {
                    accommodation.setStatus("寰呭垎閰?);
                }

                return accommodation;
            }

        } catch (SQLException e) {
            logger.error("鑾峰彇瀛︾敓浣忓淇℃伅澶辫触锛歿}", studentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public boolean assignBed(String studentId, String bedId) {
        // 妫€鏌ュ簥浣嶆槸鍚﹀凡琚崰鐢?
        if (isBedOccupied(bedId)) {
            logger.warn("搴婁綅宸茶鍗犵敤锛歿}", bedId);
            return false;
        }

        // 鍒嗛厤搴婁綅锛氭洿鏂板鐢熻〃鐨刡ed_id瀛楁
        String sql = "UPDATE student SET bed_id = ? WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);
            stmt.setString(2, studentId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("鍒嗛厤搴婁綅鎴愬姛锛氬鐢焮} -> 搴婁綅{}", studentId, bedId);
                return true;
            }

        } catch (SQLException e) {
            logger.error("鍒嗛厤搴婁綅澶辫触锛氬鐢焮} -> 搴婁綅{}", studentId, bedId, e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return false;
    }

    @Override
    public boolean changeBed(String studentId, String newBedId) {
        // 妫€鏌ユ柊搴婁綅鏄惁宸茶鍗犵敤
        if (isBedOccupied(newBedId)) {
            logger.warn("鏂板簥浣嶅凡琚崰鐢細{}", newBedId);
            return false;
        }

        // 璋冩崲搴婁綅锛氭洿鏂板鐢熻〃鐨刡ed_id瀛楁
        String sql = "UPDATE student SET bed_id = ? WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newBedId);
            stmt.setString(2, studentId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("璋冩崲搴婁綅鎴愬姛锛氬鐢焮} -> 鏂板簥浣峽}", studentId, newBedId);
                return true;
            }

        } catch (SQLException e) {
            logger.error("璋冩崲搴婁綅澶辫触锛氬鐢焮} -> 鏂板簥浣峽}", studentId, newBedId, e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return false;
    }

    @Override
    public boolean checkout(String studentId) {
        // 閫€瀹匡細娓呯┖瀛︾敓琛ㄧ殑bed_id瀛楁
        String sql = "UPDATE student SET bed_id = NULL WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("閫€瀹挎垚鍔燂細瀛︾敓{}", studentId);
                return true;
            }

        } catch (SQLException e) {
            logger.error("閫€瀹垮け璐ワ細瀛︾敓{}", studentId, e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return false;
    }

    @Override
    public List<String> findAvailableBeds() {
        String sql = "SELECT bed_id FROM bed WHERE bed_id NOT IN (SELECT bed_id FROM student WHERE bed_id IS NOT NULL) ORDER BY bed_id";
        List<String> availableBeds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                availableBeds.add(rs.getString("bed_id"));
            }

            logger.info("鑾峰彇绌洪棽搴婁綅鎴愬姛锛歿}涓?, availableBeds.size());

        } catch (SQLException e) {
            logger.error("鑾峰彇绌洪棽搴婁綅澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return availableBeds;
    }

    /**
     * 妫€鏌ュ簥浣嶆槸鍚﹀凡琚崰鐢?
     */
    private boolean isBedOccupied(String bedId) {
        String sql = "SELECT COUNT(*) FROM student WHERE bed_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            logger.error("妫€鏌ュ簥浣嶅崰鐢ㄧ姸鎬佸け璐ワ細{}", bedId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return false;
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴ResultSet澶辫触", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴PreparedStatement澶辫触", e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴Connection澶辫触", e);
            }
        }
    }
}

========================================
AnnouncementDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.AnnouncementDAO;
import org.dorm.model.entity.Announcement;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 鍏憡鏁版嵁璁块棶瀹炵幇绫?
 */
public class AnnouncementDAOImpl implements AnnouncementDAO {
    private static final Logger logger = LoggerFactory.getLogger(AnnouncementDAOImpl.class);

    @Override
    public List<Announcement> findAllActive() {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement WHERE status = 'active' ORDER BY publish_time DESC";
        List<Announcement> announcements = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鎵€鏈夋縺娲诲叕鍛婂け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return announcements;
    }

    @Override
    public List<Announcement> findAll() {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement ORDER BY publish_time DESC";
        List<Announcement> announcements = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鎵€鏈夊叕鍛婂け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return announcements;
    }

    @Override
    public Announcement findById(String announcementId) {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement WHERE announcement_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, announcementId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                return announcement;
            }
        } catch (SQLException e) {
            logger.error("鏍规嵁ID鏌ユ壘鍏憡澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public boolean insert(Announcement announcement) {
        String sql = "INSERT INTO announcement (announcement_id, title, content, publisher, publish_time, status, category) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, announcement.getAnnouncementId());
            stmt.setString(2, announcement.getTitle());
            stmt.setString(3, announcement.getContent());
            stmt.setString(4, announcement.getPublisher());
            stmt.setTimestamp(5, new Timestamp(announcement.getPublishTime().getTime()));
            stmt.setString(6, announcement.getStatus());
            stmt.setString(7, announcement.getCategory());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("鍏憡鎻掑叆鎴愬姛锛歿}", announcement.getAnnouncementId());
                return true;
            }
        } catch (SQLException e) {
            logger.error("鎻掑叆鍏憡澶辫触", e);
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    @Override
    public boolean update(Announcement announcement) {
        String sql = "UPDATE announcement SET title = ?, content = ?, status = ?, category = ? WHERE announcement_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, announcement.getTitle());
            stmt.setString(2, announcement.getContent());
            stmt.setString(3, announcement.getStatus());
            stmt.setString(4, announcement.getCategory());
            stmt.setString(5, announcement.getAnnouncementId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("鍏憡鏇存柊鎴愬姛锛歿}", announcement.getAnnouncementId());
                return true;
            }
        } catch (SQLException e) {
            logger.error("鏇存柊鍏憡澶辫触", e);
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    @Override
    public boolean delete(String announcementId) {
        String sql = "DELETE FROM announcement WHERE announcement_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, announcementId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("鍏憡鍒犻櫎鎴愬姛锛歿}", announcementId);
                return true;
            }
        } catch (SQLException e) {
            logger.error("鍒犻櫎鍏憡澶辫触", e);
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    @Override
    public boolean updateStatus(String announcementId, String status) {
        String sql = "UPDATE announcement SET status = ? WHERE announcement_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setString(2, announcementId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("鍏憡鐘舵€佹洿鏂版垚鍔燂細{} -> {}", announcementId, status);
                return true;
            }
        } catch (SQLException e) {
            logger.error("鏇存柊鍏憡鐘舵€佸け璐?, e);
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    @Override
    public List<Announcement> findByCategory(String category) {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement WHERE category = ? AND status = 'active' ORDER BY publish_time DESC";
        List<Announcement> announcements = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, category);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            logger.error("鏍规嵁鍒嗙被鑾峰彇鍏憡澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return announcements;
    }

    @Override
    public List<Announcement> findLatest(int limit) {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement WHERE status = 'active' ORDER BY publish_time DESC LIMIT ?";
        List<Announcement> announcements = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鏈€鏂板叕鍛婂け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return announcements;
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴ResultSet澶辫触", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴PreparedStatement澶辫触", e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴Connection澶辫触", e);
            }
        }
    }
}

========================================
BedDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.BedDAO;
import org.dorm.model.entity.Bed;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 搴婁綅鏁版嵁璁块棶瀹炵幇绫?
 */
public class BedDAOImpl implements BedDAO {
    private static final Logger logger = LoggerFactory.getLogger(BedDAOImpl.class);

    @Override
    public List<Bed> findAll() {
        String sql = "SELECT bed_id, dorm_id, status, student_id FROM bed ORDER BY dorm_id, bed_id";
        List<Bed> beds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getString("bed_id"));
                bed.setDormId(rs.getString("dorm_id"));
                bed.setStatus(rs.getString("status"));
                bed.setStudentId(rs.getString("student_id"));
                beds.add(bed);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鎵€鏈夊簥浣嶅け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return beds;
    }

    @Override
    public Bed findByBedId(String bedId) {
        String sql = "SELECT bed_id, dorm_id, status, student_id FROM bed WHERE bed_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getString("bed_id"));
                bed.setDormId(rs.getString("dorm_id"));
                bed.setStatus(rs.getString("status"));
                bed.setStudentId(rs.getString("student_id"));
                return bed;
            }
        } catch (SQLException e) {
            logger.error("鏌ユ壘搴婁綅澶辫触锛歿}", bedId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public List<Bed> findAvailableBeds() {
        String sql = "SELECT bed_id, dorm_id, status, student_id FROM bed WHERE status = '绌洪棽' ORDER BY dorm_id, bed_id";
        List<Bed> beds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getString("bed_id"));
                bed.setDormId(rs.getString("dorm_id"));
                bed.setStatus(rs.getString("status"));
                bed.setStudentId(rs.getString("student_id"));
                beds.add(bed);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鍙敤搴婁綅澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return beds;
    }

    @Override
    public List<Bed> findByDormId(String dormId) {
        String sql = "SELECT bed_id, dorm_id, status, student_id FROM bed WHERE dorm_id = ? ORDER BY bed_id";
        List<Bed> beds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getString("bed_id"));
                bed.setDormId(rs.getString("dorm_id"));
                bed.setStatus(rs.getString("status"));
                bed.setStudentId(rs.getString("student_id"));
                beds.add(bed);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇瀹胯垗搴婁綅澶辫触锛歿}", dormId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return beds;
    }

    @Override
    public boolean addBed(Bed bed) {
        String sql = "INSERT INTO bed (bed_id, dorm_id, status, student_id) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bed.getBedId());
            stmt.setString(2, bed.getDormId());
            stmt.setString(3, bed.getStatus());
            stmt.setString(4, bed.getStudentId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("娣诲姞搴婁綅澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateBed(Bed bed) {
        String sql = "UPDATE bed SET dorm_id = ?, status = ?, student_id = ? WHERE bed_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bed.getDormId());
            stmt.setString(2, bed.getStatus());
            stmt.setString(3, bed.getStudentId());
            stmt.setString(4, bed.getBedId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鏇存柊搴婁綅澶辫触锛歿}", bed.getBedId(), e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteBed(String bedId) {
        String sql = "DELETE FROM bed WHERE bed_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鍒犻櫎搴婁綅澶辫触锛歿}", bedId, e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            logger.error("鍏抽棴鏁版嵁搴撹祫婧愬け璐?, e);
        }
    }
}

========================================
DormitoryDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.DormitoryDAO;
import org.dorm.model.entity.Dormitory;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 瀹胯垗鏁版嵁璁块棶瀹炵幇绫?
 */
public class DormitoryDAOImpl implements DormitoryDAO {
    private static final Logger logger = LoggerFactory.getLogger(DormitoryDAOImpl.class);

    @Override
    public Dormitory findByDormitoryId(String dormitoryId) {
        String sql = "SELECT dorm_id, building, floor, capacity, manager_name, manager_phone FROM dormitory WHERE dorm_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormitoryId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryId(rs.getString("dorm_id"));
                dormitory.setBuilding(rs.getString("building"));
                dormitory.setFloor(rs.getInt("floor"));
                dormitory.setTotalBeds(rs.getInt("capacity"));
                dormitory.setManagerName(rs.getString("manager_name"));
                dormitory.setManagerPhone(rs.getString("manager_phone"));
                return dormitory;
            }
        } catch (SQLException e) {
            logger.error("鏌ユ壘瀹胯垗澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public List<Dormitory> findAll() {
        String sql = "SELECT dorm_id, building, floor, capacity, manager_name, manager_phone FROM dormitory ORDER BY dorm_id";
        List<Dormitory> dormitories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryId(rs.getString("dorm_id"));
                dormitory.setBuilding(rs.getString("building"));
                dormitory.setFloor(rs.getInt("floor"));
                dormitory.setTotalBeds(rs.getInt("capacity"));
                dormitory.setManagerName(rs.getString("manager_name"));
                dormitory.setManagerPhone(rs.getString("manager_phone"));
                dormitories.add(dormitory);
            }
        } catch (SQLException e) {
            logger.error("鏌ユ壘鎵€鏈夊鑸嶅け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return dormitories;
    }

    @Override
    public boolean addDormitory(Dormitory dormitory) {
        String sql = "INSERT INTO dormitory (dorm_id, building, floor, capacity, manager_name, manager_phone) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormitory.getDormitoryId());
            stmt.setString(2, dormitory.getBuilding());
            stmt.setInt(3, dormitory.getFloor());
            stmt.setInt(4, dormitory.getTotalBeds());
            stmt.setString(5, dormitory.getManagerName());
            stmt.setString(6, dormitory.getManagerPhone());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("娣诲姞瀹胯垗澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateDormitory(Dormitory dormitory) {
        String sql = "UPDATE dormitory SET building = ?, floor = ?, capacity = ?, manager_name = ?, manager_phone = ? WHERE dorm_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormitory.getBuilding());
            stmt.setInt(2, dormitory.getFloor());
            stmt.setInt(3, dormitory.getTotalBeds());
            stmt.setString(4, dormitory.getManagerName());
            stmt.setString(5, dormitory.getManagerPhone());
            stmt.setString(6, dormitory.getDormitoryId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("鏇存柊瀹胯垗澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteDormitory(String dormitoryId) {
        String sql = "DELETE FROM dormitory WHERE dorm_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormitoryId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("鍒犻櫎瀹胯垗澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.warn("鍏抽棴ResultSet澶辫触", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.warn("鍏抽棴PreparedStatement澶辫触", e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.warn("鍏抽棴Connection澶辫触", e);
            }
        }
    }
}

========================================
PaymentDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.PaymentDAO;
import org.dorm.model.entity.Payment;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 缂磋垂鏁版嵁璁块棶瀹炵幇绫?
 */
public class PaymentDAOImpl implements PaymentDAO {
    private static final Logger logger = LoggerFactory.getLogger(PaymentDAOImpl.class);

    @Override
    public List<Payment> findAll() {
        String sql = "SELECT payment_id, student_id, amount, payment_date, semester, status FROM payment ORDER BY payment_date DESC";
        List<Payment> payments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("payment_id"));
                payment.setStudentId(rs.getString("student_id"));
                payment.setAmount(rs.getBigDecimal("amount"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setSemester(rs.getString("semester"));
                payment.setStatus(rs.getString("status"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鎵€鏈夌即璐硅褰曞け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return payments;
    }

    @Override
    public Payment findByPaymentId(String paymentId) {
        String sql = "SELECT payment_id, student_id, amount, payment_date, semester, status FROM payment WHERE payment_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("payment_id"));
                payment.setStudentId(rs.getString("student_id"));
                payment.setAmount(rs.getBigDecimal("amount"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setSemester(rs.getString("semester"));
                payment.setStatus(rs.getString("status"));
                return payment;
            }
        } catch (SQLException e) {
            logger.error("鏌ユ壘缂磋垂璁板綍澶辫触锛歿}", paymentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public List<Payment> findByStudentId(String studentId) {
        String sql = "SELECT payment_id, student_id, amount, payment_date, semester, status FROM payment WHERE student_id = ? ORDER BY payment_date DESC";
        List<Payment> payments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("payment_id"));
                payment.setStudentId(rs.getString("student_id"));
                payment.setAmount(rs.getBigDecimal("amount"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setSemester(rs.getString("semester"));
                payment.setStatus(rs.getString("status"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇瀛︾敓缂磋垂璁板綍澶辫触锛歿}", studentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return payments;
    }

    @Override
    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payment (payment_id, student_id, amount, payment_date, semester, status) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, payment.getPaymentId());
            stmt.setString(2, payment.getStudentId());
            stmt.setBigDecimal(3, payment.getAmount());
            stmt.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            stmt.setString(5, payment.getSemester());
            stmt.setString(6, payment.getStatus());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("娣诲姞缂磋垂璁板綍澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updatePayment(Payment payment) {
        String sql = "UPDATE payment SET student_id = ?, amount = ?, payment_date = ?, semester = ?, status = ? WHERE payment_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, payment.getStudentId());
            stmt.setBigDecimal(2, payment.getAmount());
            stmt.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
            stmt.setString(4, payment.getSemester());
            stmt.setString(5, payment.getStatus());
            stmt.setString(6, payment.getPaymentId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鏇存柊缂磋垂璁板綍澶辫触锛歿}", payment.getPaymentId(), e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deletePayment(String paymentId) {
        String sql = "DELETE FROM payment WHERE payment_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鍒犻櫎缂磋垂璁板綍澶辫触锛歿}", paymentId, e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            logger.error("鍏抽棴鏁版嵁搴撹祫婧愬け璐?, e);
        }
    }
}

========================================
ReminderDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.ReminderDAO;
import org.dorm.model.entity.Reminder;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 鎻愰啋鏁版嵁璁块棶瀹炵幇绫? */
public class ReminderDAOImpl implements ReminderDAO {
    private static final Logger logger = LoggerFactory.getLogger(ReminderDAOImpl.class);

    @Override
    public List<Reminder> findAll() {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder ORDER BY create_time DESC";
        List<Reminder> reminders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鎵€鏈夋彁閱掑け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return reminders;
    }

    @Override
    public Reminder findByReminderId(String reminderId) {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder WHERE reminder_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reminderId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                return reminder;
            }
        } catch (SQLException e) {
            logger.error("鏍规嵁ID鏌ユ壘鎻愰啋澶辫触锛歿}", reminderId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public List<Reminder> findByStudentId(String studentId) {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder WHERE student_id = ? ORDER BY create_time DESC";
        List<Reminder> reminders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            logger.error("鏍规嵁瀛︾敓ID鏌ユ壘鎻愰啋澶辫触锛歿}", studentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return reminders;
    }

    @Override
    public List<Reminder> findByStatus(String status) {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder WHERE status = ? ORDER BY create_time DESC";
        List<Reminder> reminders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            logger.error("鏍规嵁鐘舵€佹煡鎵炬彁閱掑け璐ワ細{}", status, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return reminders;
    }

    @Override
    public List<Reminder> findByType(String type) {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder WHERE type = ? ORDER BY create_time DESC";
        List<Reminder> reminders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            logger.error("鏍规嵁绫诲瀷鏌ユ壘鎻愰啋澶辫触锛歿}", type, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return reminders;
    }

    @Override
    public List<Reminder> findUnprocessedReminders() {
        return findByStatus("寰呭鐞?);
    }

    @Override
    public boolean addReminder(Reminder reminder) {
        String sql = "INSERT INTO reminder (reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reminder.getReminderId());
            stmt.setString(2, reminder.getStudentId());
            stmt.setString(3, reminder.getStudentName());
            stmt.setString(4, reminder.getType());
            stmt.setString(5, reminder.getTitle());
            stmt.setString(6, reminder.getContent());
            stmt.setString(7, reminder.getPriority());
            stmt.setString(8, reminder.getStatus());
            if (reminder.getCreateTime() != null) {
                stmt.setTimestamp(9, new java.sql.Timestamp(reminder.getCreateTime().getTime()));
            } else {
                stmt.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));
            }
            if (reminder.getHandleTime() != null) {
                stmt.setTimestamp(10, new java.sql.Timestamp(reminder.getHandleTime().getTime()));
            } else {
                stmt.setTimestamp(10, null);
            }
            stmt.setString(11, reminder.getHandler());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("娣诲姞鎻愰啋澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateReminder(Reminder reminder) {
        String sql = "UPDATE reminder SET student_id=?, student_name=?, type=?, title=?, content=?, priority=?, status=?, create_time=?, handle_time=?, handler=? WHERE reminder_id=?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reminder.getStudentId());
            stmt.setString(2, reminder.getStudentName());
            stmt.setString(3, reminder.getType());
            stmt.setString(4, reminder.getTitle());
            stmt.setString(5, reminder.getContent());
            stmt.setString(6, reminder.getPriority());
            stmt.setString(7, reminder.getStatus());
            if (reminder.getCreateTime() != null) {
                stmt.setTimestamp(8, new java.sql.Timestamp(reminder.getCreateTime().getTime()));
            } else {
                stmt.setTimestamp(8, null);
            }
            if (reminder.getHandleTime() != null) {
                stmt.setTimestamp(9, new java.sql.Timestamp(reminder.getHandleTime().getTime()));
            } else {
                stmt.setTimestamp(9, null);
            }
            stmt.setString(10, reminder.getHandler());
            stmt.setString(11, reminder.getReminderId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("鏇存柊鎻愰啋澶辫触锛歿}", reminder.getReminderId(), e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteReminder(String reminderId) {
        String sql = "DELETE FROM reminder WHERE reminder_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reminderId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("鍒犻櫎鎻愰啋澶辫触锛歿}", reminderId, e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public int deleteProcessedReminders() {
        String sql = "DELETE FROM reminder WHERE status = '宸插鐞?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            logger.error("鎵归噺鍒犻櫎宸插鐞嗘彁閱掑け璐?, e);
            return 0;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            logger.error("鍏抽棴鏁版嵁搴撹祫婧愬け璐?, e);
        }
    }
}

========================================
RepairApplicationDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.RepairApplicationDAO;
import org.dorm.model.entity.RepairApplication;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 缁翠慨鐢宠鏁版嵁璁块棶瀹炵幇绫?
 */
public class RepairApplicationDAOImpl implements RepairApplicationDAO {
    private static final Logger logger = LoggerFactory.getLogger(RepairApplicationDAOImpl.class);

    @Override
    public RepairApplication findByApplyId(String applyId) {
        String sql = "SELECT apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status, handler, finish_time FROM repair_application WHERE apply_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, applyId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                RepairApplication application = new RepairApplication();
                application.setApplyId(rs.getString("apply_id"));
                application.setStudentId(rs.getString("student_id"));
                application.setFaultLocation(rs.getString("fault_location"));
                application.setFaultDesc(rs.getString("fault_desc"));
                application.setContactPhone(rs.getString("contact_phone"));
                application.setApplyTime(rs.getTimestamp("apply_time"));
                application.setStatus(rs.getString("status"));
                application.setHandler(rs.getString("handler"));
                application.setFinishTime(rs.getTimestamp("finish_time"));
                return application;
            }
        } catch (SQLException e) {
            logger.error("鏌ユ壘缁翠慨鐢宠澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public List<RepairApplication> findByStudentId(String studentId) {
        String sql = "SELECT apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status, handler, finish_time FROM repair_application WHERE student_id = ? ORDER BY apply_time DESC";
        List<RepairApplication> applications = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                RepairApplication application = new RepairApplication();
                application.setApplyId(rs.getString("apply_id"));
                application.setStudentId(rs.getString("student_id"));
                application.setFaultLocation(rs.getString("fault_location"));
                application.setFaultDesc(rs.getString("fault_desc"));
                application.setContactPhone(rs.getString("contact_phone"));
                application.setApplyTime(rs.getTimestamp("apply_time"));
                application.setStatus(rs.getString("status"));
                application.setHandler(rs.getString("handler"));
                application.setFinishTime(rs.getTimestamp("finish_time"));
                applications.add(application);
            }
        } catch (SQLException e) {
            logger.error("鎸夊鐢熸煡鎵剧淮淇敵璇峰け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return applications;
    }

    @Override
    public List<RepairApplication> findAll() {
        String sql = "SELECT apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status, handler, finish_time FROM repair_application ORDER BY apply_time DESC";
        List<RepairApplication> applications = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                RepairApplication application = new RepairApplication();
                application.setApplyId(rs.getString("apply_id"));
                application.setStudentId(rs.getString("student_id"));
                application.setFaultLocation(rs.getString("fault_location"));
                application.setFaultDesc(rs.getString("fault_desc"));
                application.setContactPhone(rs.getString("contact_phone"));
                application.setApplyTime(rs.getTimestamp("apply_time"));
                application.setStatus(rs.getString("status"));
                application.setHandler(rs.getString("handler"));
                application.setFinishTime(rs.getTimestamp("finish_time"));
                applications.add(application);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鎵€鏈夌淮淇敵璇峰け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return applications;
    }

    @Override
    public List<RepairApplication> findByStatus(String status) {
        String sql = "SELECT apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status, handler, finish_time FROM repair_application WHERE status = ? ORDER BY apply_time DESC";
        List<RepairApplication> applications = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            rs = stmt.executeQuery();

            while (rs.next()) {
                RepairApplication application = new RepairApplication();
                application.setApplyId(rs.getString("apply_id"));
                application.setStudentId(rs.getString("student_id"));
                application.setFaultLocation(rs.getString("fault_location"));
                application.setFaultDesc(rs.getString("fault_desc"));
                application.setContactPhone(rs.getString("contact_phone"));
                application.setApplyTime(rs.getTimestamp("apply_time"));
                application.setStatus(rs.getString("status"));
                application.setHandler(rs.getString("handler"));
                application.setFinishTime(rs.getTimestamp("finish_time"));
                applications.add(application);
            }
        } catch (SQLException e) {
            logger.error("鎸夌姸鎬佹煡鎵剧淮淇敵璇峰け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return applications;
    }

    @Override
    public boolean addRepairApplication(RepairApplication application) {
        String sql = "INSERT INTO repair_application (apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, application.getApplyId());
            stmt.setString(2, application.getStudentId());
            stmt.setString(3, application.getFaultLocation());
            stmt.setString(4, application.getFaultDesc());
            stmt.setString(5, application.getContactPhone());
            stmt.setTimestamp(6, new Timestamp(application.getApplyTime().getTime()));
            stmt.setString(7, application.getStatus());

            int rows = stmt.executeUpdate();
            logger.info("缁翠慨鐢宠鎻掑叆鎴愬姛锛屽奖鍝嶈鏁帮細{}", rows);
            return rows > 0;
        } catch (SQLException e) {
            logger.error("娣诲姞缁翠慨鐢宠澶辫触", e);
            logger.error("SQL: {}", sql);
            logger.error("鍙傛暟: applyId={}, studentId={}, faultLocation={}, faultDesc={}, contactPhone={}, applyTime={}, status={}",
                application.getApplyId(), application.getStudentId(), application.getFaultLocation(),
                application.getFaultDesc(), application.getContactPhone(), application.getApplyTime(), application.getStatus());
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateStatus(String applyId, String status, String handler) {
        String sql = "UPDATE repair_application SET status = ?, handler = ? WHERE apply_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setString(2, handler);
            stmt.setString(3, applyId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鏇存柊缁翠慨鐢宠鐘舵€佸け璐?, e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean completeRepair(String applyId, java.util.Date finishTime) {
        String sql = "UPDATE repair_application SET status = 'completed', finish_time = ? WHERE apply_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, new Timestamp(finishTime.getTime()));
            stmt.setString(2, applyId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("瀹屾垚缁翠慨鐢宠澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴ResultSet澶辫触", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴PreparedStatement澶辫触", e);
            }
        }
        DatabaseUtil.closeConnection(conn);
    }
}

========================================
StudentDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.StudentDAO;
import org.dorm.model.entity.Student;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 瀛︾敓鏁版嵁璁块棶瀹炵幇绫?
 */
public class StudentDAOImpl implements StudentDAO {
    private static final Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);

    @Override
    public Student findByStudentId(String studentId) {
        String sql = "SELECT student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id FROM student WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setPassword(rs.getString("password"));
                student.setName(rs.getString("name"));
                student.setCollegeName(rs.getString("college_name"));
                student.setClassName(rs.getString("class_name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setBedId(rs.getString("bed_id"));
                return student;
            }
        } catch (SQLException e) {
            logger.error("鏌ユ壘瀛︾敓澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id FROM student ORDER BY student_id";
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setPassword(rs.getString("password"));
                student.setName(rs.getString("name"));
                student.setCollegeName(rs.getString("college_name"));
                student.setClassName(rs.getString("class_name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setBedId(rs.getString("bed_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鎵€鏈夊鐢熷け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return students;
    }

    @Override
    public List<Student> findByClass(String className) {
        String sql = "SELECT student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id FROM student WHERE class_name = ? ORDER BY student_id";
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, className);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setPassword(rs.getString("password"));
                student.setName(rs.getString("name"));
                student.setCollegeName(rs.getString("college_name"));
                student.setClassName(rs.getString("class_name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setBedId(rs.getString("bed_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            logger.error("鎸夌彮绾ф煡鎵惧鐢熷け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return students;
    }

    @Override
    public List<Student> findByCollege(String collegeName) {
        String sql = "SELECT student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id FROM student WHERE college_name = ? ORDER BY student_id";
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, collegeName);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setPassword(rs.getString("password"));
                student.setName(rs.getString("name"));
                student.setCollegeName(rs.getString("college_name"));
                student.setClassName(rs.getString("class_name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setBedId(rs.getString("bed_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            logger.error("鎸夊闄㈡煡鎵惧鐢熷け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return students;
    }

    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student (student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getPassword());
            stmt.setString(3, student.getName());
            stmt.setString(4, student.getCollegeName());
            stmt.setString(5, student.getClassName());
            stmt.setString(6, student.getGender());
            stmt.setDate(7, student.getBirthday() != null ? new java.sql.Date(student.getBirthday().getTime()) : null);
            stmt.setString(8, student.getEmail());
            stmt.setString(9, student.getPhone());
            stmt.setString(10, student.getBedId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("娣诲姞瀛︾敓澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        String sql = "UPDATE student SET password = ?, name = ?, college_name = ?, class_name = ?, gender = ?, birthday = ?, email = ?, phone = ?, bed_id = ? WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getPassword());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getCollegeName());
            stmt.setString(4, student.getClassName());
            stmt.setString(5, student.getGender());
            stmt.setDate(6, student.getBirthday() != null ? new java.sql.Date(student.getBirthday().getTime()) : null);
            stmt.setString(7, student.getEmail());
            stmt.setString(8, student.getPhone());
            stmt.setString(9, student.getBedId());
            stmt.setString(10, student.getStudentId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鏇存柊瀛︾敓澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM student WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鍒犻櫎瀛︾敓澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateStudentBed(String studentId, String bedId) {
        String sql = "UPDATE student SET bed_id = ? WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);
            stmt.setString(2, studentId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鏇存柊瀛︾敓搴婁綅澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴ResultSet澶辫触", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴PreparedStatement澶辫触", e);
            }
        }
        DatabaseUtil.closeConnection(conn);
    }
}

========================================
UserDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.UserDAO;
import org.dorm.model.entity.User;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 鐢ㄦ埛鏁版嵁璁块棶瀹炵幇绫?
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public User findByUserId(String userId) {
        String sql = "SELECT user_id, password, user_type FROM user WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
                return user;
            }
        } catch (SQLException e) {
            logger.error("鏌ユ壘鐢ㄦ埛澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public User login(String userId, String password) {
        String sql = "SELECT user_id, password, user_type FROM user WHERE user_id = ? AND password = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
                logger.info("鐢ㄦ埛鐧诲綍鎴愬姛: {}", userId);
                return user;
            }
        } catch (SQLException e) {
            logger.error("鐢ㄦ埛鐧诲綍楠岃瘉澶辫触", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO user (user_id, password, user_type) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserType());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("娣诲姞鐢ㄦ埛澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE user SET password = ?, user_type = ? WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getUserType());
            stmt.setString(3, user.getUserId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鏇存柊鐢ㄦ埛澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteUser(String userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鍒犻櫎鐢ㄦ埛澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴ResultSet澶辫触", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("鍏抽棴PreparedStatement澶辫触", e);
            }
        }
        DatabaseUtil.closeConnection(conn);
    }
}

========================================
ViolationDAOImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao\impl
========================================
package org.dorm.model.dao.impl;

import org.dorm.model.dao.ViolationDAO;
import org.dorm.model.entity.Violation;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 杩濈邯鏁版嵁璁块棶瀹炵幇绫?
 */
public class ViolationDAOImpl implements ViolationDAO {
    private static final Logger logger = LoggerFactory.getLogger(ViolationDAOImpl.class);

    @Override
    public List<Violation> findAll() {
        String sql = "SELECT violation_id, student_id, description, violation_date, penalty FROM violation ORDER BY violation_date DESC";
        List<Violation> violations = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Violation violation = new Violation();
                violation.setViolationId(rs.getString("violation_id"));
                violation.setStudentId(rs.getString("student_id"));
                violation.setDescription(rs.getString("description"));
                violation.setViolationDate(rs.getDate("violation_date"));
                violation.setPenalty(rs.getString("penalty"));
                violations.add(violation);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇鎵€鏈夎繚绾褰曞け璐?, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return violations;
    }

    @Override
    public Violation findByViolationId(String violationId) {
        String sql = "SELECT violation_id, student_id, description, violation_date, penalty FROM violation WHERE violation_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, violationId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Violation violation = new Violation();
                violation.setViolationId(rs.getString("violation_id"));
                violation.setStudentId(rs.getString("student_id"));
                violation.setDescription(rs.getString("description"));
                violation.setViolationDate(rs.getDate("violation_date"));
                violation.setPenalty(rs.getString("penalty"));
                return violation;
            }
        } catch (SQLException e) {
            logger.error("鏌ユ壘杩濈邯璁板綍澶辫触锛歿}", violationId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public List<Violation> findByStudentId(String studentId) {
        String sql = "SELECT violation_id, student_id, description, violation_date, penalty FROM violation WHERE student_id = ? ORDER BY violation_date DESC";
        List<Violation> violations = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Violation violation = new Violation();
                violation.setViolationId(rs.getString("violation_id"));
                violation.setStudentId(rs.getString("student_id"));
                violation.setDescription(rs.getString("description"));
                violation.setViolationDate(rs.getDate("violation_date"));
                violation.setPenalty(rs.getString("penalty"));
                violations.add(violation);
            }
        } catch (SQLException e) {
            logger.error("鑾峰彇瀛︾敓杩濈邯璁板綍澶辫触锛歿}", studentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return violations;
    }

    @Override
    public boolean addViolation(Violation violation) {
        String sql = "INSERT INTO violation (violation_id, student_id, description, violation_date, penalty) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, violation.getViolationId());
            stmt.setString(2, violation.getStudentId());
            stmt.setString(3, violation.getDescription());
            stmt.setDate(4, new java.sql.Date(violation.getViolationDate().getTime()));
            stmt.setString(5, violation.getPenalty());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("娣诲姞杩濈邯璁板綍澶辫触", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateViolation(Violation violation) {
        String sql = "UPDATE violation SET student_id = ?, description = ?, violation_date = ?, penalty = ? WHERE violation_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, violation.getStudentId());
            stmt.setString(2, violation.getDescription());
            stmt.setDate(3, new java.sql.Date(violation.getViolationDate().getTime()));
            stmt.setString(4, violation.getPenalty());
            stmt.setString(5, violation.getViolationId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鏇存柊杩濈邯璁板綍澶辫触锛歿}", violation.getViolationId(), e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteViolation(String violationId) {
        String sql = "DELETE FROM violation WHERE violation_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, violationId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("鍒犻櫎杩濈邯璁板綍澶辫触锛歿}", violationId, e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 鍏抽棴鏁版嵁搴撹祫婧?
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            logger.error("鍏抽棴鏁版嵁搴撹祫婧愬け璐?, e);
        }
    }
}

========================================
AccommodationDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.Accommodation;
import java.util.List;

/**
 * 浣忓鍒嗛厤鏁版嵁璁块棶鎺ュ彛
 */
public interface AccommodationDAO {
    /**
     * 鑾峰彇鎵€鏈変綇瀹夸俊鎭?
     * @return 浣忓淇℃伅鍒楄〃
     */
    List<Accommodation> findAll();

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇浣忓淇℃伅
     * @param studentId 瀛︾敓ID
     * @return 浣忓淇℃伅
     */
    Accommodation findByStudentId(String studentId);

    /**
     * 鍒嗛厤搴婁綅
     * @param studentId 瀛︾敓ID
     * @param bedId 搴婁綅ID
     * @return 鏄惁鎴愬姛
     */
    boolean assignBed(String studentId, String bedId);

    /**
     * 璋冩崲搴婁綅
     * @param studentId 瀛︾敓ID
     * @param newBedId 鏂板簥浣岻D
     * @return 鏄惁鎴愬姛
     */
    boolean changeBed(String studentId, String newBedId);

    /**
     * 閫€瀹?
     * @param studentId 瀛︾敓ID
     * @return 鏄惁鎴愬姛
     */
    boolean checkout(String studentId);

    /**
     * 鑾峰彇绌洪棽搴婁綅鍒楄〃
     * @return 绌洪棽搴婁綅ID鍒楄〃
     */
    List<String> findAvailableBeds();
}

========================================
AnnouncementDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.Announcement;
import java.util.List;

/**
 * 鍏憡鏁版嵁璁块棶瀵硅薄鎺ュ彛
 */
public interface AnnouncementDAO {

    /**
     * 鑾峰彇鎵€鏈夋縺娲荤姸鎬佺殑鍏憡
     * @return 鍏憡鍒楄〃
     */
    List<Announcement> findAllActive();

    /**
     * 鑾峰彇鎵€鏈夊叕鍛婏紙鍖呮嫭鍋滅敤鐨勶級
     * @return 鍏憡鍒楄〃
     */
    List<Announcement> findAll();

    /**
     * 鏍规嵁ID鑾峰彇鍏憡
     * @param announcementId 鍏憡ID
     * @return 鍏憡瀵硅薄
     */
    Announcement findById(String announcementId);

    /**
     * 娣诲姞鏂板叕鍛?
     * @param announcement 鍏憡瀵硅薄
     * @return 鏄惁鎴愬姛
     */
    boolean insert(Announcement announcement);

    /**
     * 鏇存柊鍏憡淇℃伅
     * @param announcement 鍏憡瀵硅薄
     * @return 鏄惁鎴愬姛
     */
    boolean update(Announcement announcement);

    /**
     * 鍒犻櫎鍏憡
     * @param announcementId 鍏憡ID
     * @return 鏄惁鎴愬姛
     */
    boolean delete(String announcementId);

    /**
     * 鏇存柊鍏憡鐘舵€?
     * @param announcementId 鍏憡ID
     * @param status 鏂扮姸鎬?
     * @return 鏄惁鎴愬姛
     */
    boolean updateStatus(String announcementId, String status);

    /**
     * 鏍规嵁鍒嗙被鑾峰彇鍏憡
     * @param category 鍒嗙被
     * @return 鍏憡鍒楄〃
     */
    List<Announcement> findByCategory(String category);

    /**
     * 鑾峰彇鏈€鏂扮殑N鏉″叕鍛?
     * @param limit 鏁伴噺闄愬埗
     * @return 鍏憡鍒楄〃
     */
    List<Announcement> findLatest(int limit);
}

========================================
BedDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.Bed;

import java.util.List;

/**
 * 搴婁綅鏁版嵁璁块棶鎺ュ彛
 */
public interface BedDAO {
    /**
     * 鑾峰彇鎵€鏈夊簥浣?
     * @return 搴婁綅鍒楄〃
     */
    List<Bed> findAll();

    /**
     * 鏍规嵁搴婁綅ID鏌ユ壘搴婁綅
     * @param bedId 搴婁綅ID
     * @return 搴婁綅
     */
    Bed findByBedId(String bedId);

    /**
     * 鑾峰彇鍙敤搴婁綅
     * @return 鍙敤搴婁綅鍒楄〃
     */
    List<Bed> findAvailableBeds();

    /**
     * 鏍规嵁瀹胯垗ID鏌ユ壘搴婁綅
     * @param dormId 瀹胯垗ID
     * @return 搴婁綅鍒楄〃
     */
    List<Bed> findByDormId(String dormId);

    /**
     * 娣诲姞搴婁綅
     * @param bed 搴婁綅
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addBed(Bed bed);

    /**
     * 鏇存柊搴婁綅
     * @param bed 搴婁綅
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateBed(Bed bed);

    /**
     * 鍒犻櫎搴婁綅
     * @param bedId 搴婁綅ID
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteBed(String bedId);
}

========================================
DormitoryDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.Dormitory;

import java.util.List;

/**
 * 瀹胯垗鏁版嵁璁块棶鎺ュ彛
 */
public interface DormitoryDAO {

    /**
     * 鏌ユ壘鎵€鏈夊鑸?
     * @return 瀹胯垗鍒楄〃
     */
    List<Dormitory> findAll();

    /**
     * 鏍规嵁瀹胯垗ID鏌ユ壘瀹胯垗
     * @param dormitoryId 瀹胯垗ID
     * @return 瀹胯垗淇℃伅
     */
    Dormitory findByDormitoryId(String dormitoryId);

    /**
     * 娣诲姞瀹胯垗
     * @param dormitory 瀹胯垗淇℃伅
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addDormitory(Dormitory dormitory);

    /**
     * 鏇存柊瀹胯垗淇℃伅
     * @param dormitory 瀹胯垗淇℃伅
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateDormitory(Dormitory dormitory);

    /**
     * 鍒犻櫎瀹胯垗
     * @param dormitoryId 瀹胯垗ID
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteDormitory(String dormitoryId);
}

========================================
PaymentDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.Payment;

import java.util.List;

/**
 * 缂磋垂鏁版嵁璁块棶鎺ュ彛
 */
public interface PaymentDAO {
    /**
     * 鑾峰彇鎵€鏈夌即璐硅褰?
     * @return 缂磋垂璁板綍鍒楄〃
     */
    List<Payment> findAll();

    /**
     * 鏍规嵁缂磋垂鍗曞彿鏌ユ壘缂磋垂璁板綍
     * @param paymentId 缂磋垂鍗曞彿
     * @return 缂磋垂璁板綍
     */
    Payment findByPaymentId(String paymentId);

    /**
     * 鏍规嵁瀛︾敓ID鏌ユ壘缂磋垂璁板綍
     * @param studentId 瀛︾敓ID
     * @return 缂磋垂璁板綍鍒楄〃
     */
    List<Payment> findByStudentId(String studentId);

    /**
     * 娣诲姞缂磋垂璁板綍
     * @param payment 缂磋垂璁板綍
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addPayment(Payment payment);

    /**
     * 鏇存柊缂磋垂璁板綍
     * @param payment 缂磋垂璁板綍
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updatePayment(Payment payment);

    /**
     * 鍒犻櫎缂磋垂璁板綍
     * @param paymentId 缂磋垂鍗曞彿
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deletePayment(String paymentId);
}

========================================
ReminderDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.Reminder;

import java.util.List;

/**
 * 鎻愰啋鏁版嵁璁块棶鎺ュ彛
 */
public interface ReminderDAO {

    /**
     * 鑾峰彇鎵€鏈夋彁閱?     * @return 鎻愰啋鍒楄〃
     */
    List<Reminder> findAll();

    /**
     * 鏍规嵁鎻愰啋ID鏌ユ壘鎻愰啋
     * @param reminderId 鎻愰啋ID
     * @return 鎻愰啋淇℃伅
     */
    Reminder findByReminderId(String reminderId);

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇鎻愰啋鍒楄〃
     * @param studentId 瀛︾敓ID
     * @return 鎻愰啋鍒楄〃
     */
    List<Reminder> findByStudentId(String studentId);

    /**
     * 鏍规嵁鐘舵€佽幏鍙栨彁閱掑垪琛?     * @param status 鐘舵€?     * @return 鎻愰啋鍒楄〃
     */
    List<Reminder> findByStatus(String status);

    /**
     * 鏍规嵁绫诲瀷鑾峰彇鎻愰啋鍒楄〃
     * @param type 绫诲瀷
     * @return 鎻愰啋鍒楄〃
     */
    List<Reminder> findByType(String type);

    /**
     * 鑾峰彇鏈鐞嗙殑鎻愰啋
     * @return 鏈鐞嗘彁閱掑垪琛?     */
    List<Reminder> findUnprocessedReminders();

    /**
     * 娣诲姞鎻愰啋
     * @param reminder 鎻愰啋淇℃伅
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addReminder(Reminder reminder);

    /**
     * 鏇存柊鎻愰啋
     * @param reminder 鎻愰啋淇℃伅
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateReminder(Reminder reminder);

    /**
     * 鍒犻櫎鎻愰啋
     * @param reminderId 鎻愰啋ID
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteReminder(String reminderId);

    /**
     * 鎵归噺鍒犻櫎宸插鐞嗙殑鎻愰啋
     * @return 鍒犻櫎鐨勮褰曟暟
     */
    int deleteProcessedReminders();
}

========================================
RepairApplicationDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.RepairApplication;
import java.util.List;

/**
 * 缁翠慨鐢宠鏁版嵁璁块棶鎺ュ彛
 */
public interface RepairApplicationDAO {
    /**
     * 鏍规嵁鐢宠鍗曞彿鏌ユ壘缁翠慨鐢宠
     * @param applyId 鐢宠鍗曞彿
     * @return 缁翠慨鐢宠瀵硅薄锛屽鏋滀笉瀛樺湪杩斿洖null
     */
    RepairApplication findByApplyId(String applyId);

    /**
     * 鏍规嵁瀛︾敓瀛﹀彿鏌ユ壘缁翠慨鐢宠
     * @param studentId 瀛︾敓瀛﹀彿
     * @return 缁翠慨鐢宠鍒楄〃
     */
    List<RepairApplication> findByStudentId(String studentId);

    /**
     * 鑾峰彇鎵€鏈夌淮淇敵璇?
     * @return 缁翠慨鐢宠鍒楄〃
     */
    List<RepairApplication> findAll();

    /**
     * 鏍规嵁鐘舵€佹煡鎵剧淮淇敵璇?
     * @param status 澶勭悊鐘舵€?
     * @return 缁翠慨鐢宠鍒楄〃
     */
    List<RepairApplication> findByStatus(String status);

    /**
     * 娣诲姞缁翠慨鐢宠
     * @param application 缁翠慨鐢宠瀵硅薄
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addRepairApplication(RepairApplication application);

    /**
     * 鏇存柊缁翠慨鐢宠鐘舵€?
     * @param applyId 鐢宠鍗曞彿
     * @param status 鏂扮姸鎬?
     * @param handler 澶勭悊浜?
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateStatus(String applyId, String status, String handler);

    /**
     * 瀹屾垚缁翠慨鐢宠
     * @param applyId 鐢宠鍗曞彿
     * @param finishTime 瀹屾垚鏃堕棿
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean completeRepair(String applyId, java.util.Date finishTime);
}

========================================
StudentDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.Student;
import java.util.List;

/**
 * 瀛︾敓鏁版嵁璁块棶鎺ュ彛
 */
public interface StudentDAO {
    /**
     * 鏍规嵁瀛﹀彿鏌ユ壘瀛︾敓
     * @param studentId 瀛﹀彿
     * @return 瀛︾敓瀵硅薄锛屽鏋滀笉瀛樺湪杩斿洖null
     */
    Student findByStudentId(String studentId);

    /**
     * 鑾峰彇鎵€鏈夊鐢熷垪琛?
     * @return 瀛︾敓鍒楄〃
     */
    List<Student> findAll();

    /**
     * 鏍规嵁鐝骇鏌ユ壘瀛︾敓
     * @param className 鐝骇鍚?
     * @return 瀛︾敓鍒楄〃
     */
    List<Student> findByClass(String className);

    /**
     * 鏍规嵁瀛﹂櫌鏌ユ壘瀛︾敓
     * @param collegeName 瀛﹂櫌鍚?
     * @return 瀛︾敓鍒楄〃
     */
    List<Student> findByCollege(String collegeName);

    /**
     * 娣诲姞鏂板鐢?
     * @param student 瀛︾敓瀵硅薄
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addStudent(Student student);

    /**
     * 鏇存柊瀛︾敓淇℃伅
     * @param student 瀛︾敓瀵硅薄
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateStudent(Student student);

    /**
     * 鍒犻櫎瀛︾敓
     * @param studentId 瀛﹀彿
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteStudent(String studentId);

    /**
     * 鏇存柊瀛︾敓搴婁綅淇℃伅
     * @param studentId 瀛﹀彿
     * @param bedId 搴婁綅鍙?
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateStudentBed(String studentId, String bedId);
}

========================================
UserDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.User;

/**
 * 鐢ㄦ埛鏁版嵁璁块棶鎺ュ彛
 */
public interface UserDAO {
    /**
     * 鏍规嵁鐢ㄦ埛鍚嶆煡鎵剧敤鎴?
     * @param userId 鐢ㄦ埛鍚?
     * @return 鐢ㄦ埛瀵硅薄锛屽鏋滀笉瀛樺湪杩斿洖null
     */
    User findByUserId(String userId);

    /**
     * 楠岃瘉鐢ㄦ埛鐧诲綍
     * @param userId 鐢ㄦ埛鍚?
     * @param password 瀵嗙爜
     * @return 鐢ㄦ埛瀵硅薄锛屽鏋滈獙璇佸け璐ヨ繑鍥瀗ull
     */
    User login(String userId, String password);

    /**
     * 娣诲姞鏂扮敤鎴?
     * @param user 鐢ㄦ埛瀵硅薄
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addUser(User user);

    /**
     * 鏇存柊鐢ㄦ埛淇℃伅
     * @param user 鐢ㄦ埛瀵硅薄
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateUser(User user);

    /**
     * 鍒犻櫎鐢ㄦ埛
     * @param userId 鐢ㄦ埛鍚?
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteUser(String userId);
}

========================================
ViolationDAO.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\dao
========================================
package org.dorm.model.dao;

import org.dorm.model.entity.Violation;

import java.util.List;

/**
 * 杩濈邯鏁版嵁璁块棶鎺ュ彛
 */
public interface ViolationDAO {
    /**
     * 鑾峰彇鎵€鏈夎繚绾褰?
     * @return 杩濈邯璁板綍鍒楄〃
     */
    List<Violation> findAll();

    /**
     * 鏍规嵁杩濈邯鍗曞彿鏌ユ壘杩濈邯璁板綍
     * @param violationId 杩濈邯鍗曞彿
     * @return 杩濈邯璁板綍
     */
    Violation findByViolationId(String violationId);

    /**
     * 鏍规嵁瀛︾敓ID鏌ユ壘杩濈邯璁板綍
     * @param studentId 瀛︾敓ID
     * @return 杩濈邯璁板綍鍒楄〃
     */
    List<Violation> findByStudentId(String studentId);

    /**
     * 娣诲姞杩濈邯璁板綍
     * @param violation 杩濈邯璁板綍
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addViolation(Violation violation);

    /**
     * 鏇存柊杩濈邯璁板綍
     * @param violation 杩濈邯璁板綍
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateViolation(Violation violation);

    /**
     * 鍒犻櫎杩濈邯璁板綍
     * @param violationId 杩濈邯鍗曞彿
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteViolation(String violationId);
}

========================================
Accommodation.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

import java.util.Date;

/**
 * 浣忓淇℃伅瀹炰綋绫?
 * 鐢ㄤ簬鏄剧ず瀛︾敓鐨勪綇瀹垮垎閰嶆儏鍐?
 */
public class Accommodation {
    private String studentId;
    private String studentName;
    private String collegeName;
    private String className;
    private String dormitoryId;
    private String bedId;
    private Date checkinDate;
    private String status; // "宸插叆浣?, "寰呭垎閰?, "宸查€€瀹?

    public Accommodation() {}

    public Accommodation(String studentId, String studentName, String collegeName,
                        String className, String dormitoryId, String bedId,
                        Date checkinDate, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.collegeName = collegeName;
        this.className = className;
        this.dormitoryId = dormitoryId;
        this.bedId = bedId;
        this.checkinDate = checkinDate;
        this.status = status;
    }

    // Getter and Setter methods
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", dormitoryId='" + dormitoryId + '\'' +
                ", bedId='" + bedId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

========================================
Announcement.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

import java.util.Date;

/**
 * 鍏憡瀹炰綋绫?
 * 鐢ㄤ簬瀛樺偍绠＄悊鍛樺彂甯冪殑鍏憡淇℃伅锛屾墍鏈夊鐢熼兘鑳芥煡鐪?
 */
public class Announcement {
    private String announcementId;     // 鍏憡ID锛屽敮涓€鏍囪瘑
    private String title;              // 鍏憡鏍囬
    private String content;            // 鍏憡鍐呭
    private String publisher;          // 鍙戝竷鑰咃紙绠＄悊鍛業D锛?
    private Date publishTime;          // 鍙戝竷鏃堕棿
    private String status;             // 鐘舵€侊細active-婵€娲伙紝inactive-鍋滅敤
    private String category;           // 鍒嗙被锛歡eneral-涓€鑸€氱煡锛宨mportant-閲嶈閫氱煡锛宔mergency-绱ф€ラ€氱煡

    // 鏋勯€犲嚱鏁?
    public Announcement() {}

    public Announcement(String announcementId, String title, String content, String publisher, Date publishTime, String status, String category) {
        this.announcementId = announcementId;
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.publishTime = publishTime;
        this.status = status;
        this.category = category;
    }

    // Getter鍜孲etter鏂规硶
    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId='" + announcementId + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishTime=" + publishTime +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

========================================
Bed.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

/**
 * 搴婁綅瀹炰綋绫?
 * 鐢ㄤ簬瀛樺偍搴婁綅鍩烘湰淇℃伅
 */
public class Bed {
    private String bedId;        // 搴婁綅鍙凤紝鍞竴鏍囪瘑
    private String dormId;       // 鎵€灞炲鑸嶅彿
    private String status;       // 鍗犵敤鐘舵€侊細绌洪棽/鍗犵敤
    private String studentId;    // 鍏宠仈瀛︾敓瀛﹀彿

    // 鏋勯€犲嚱鏁?
    public Bed() {}

    public Bed(String bedId, String dormId, String status, String studentId) {
        this.bedId = bedId;
        this.dormId = dormId;
        this.status = status;
        this.studentId = studentId;
    }

    // Getter鍜孲etter鏂规硶
    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "bedId='" + bedId + '\'' +
                ", dormId='" + dormId + '\'' +
                ", status='" + status + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}

========================================
Dormitory.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

/**
 * 瀹胯垗瀹炰綋绫?
 * 鐢ㄤ簬瀛樺偍瀹胯垗鍩烘湰淇℃伅
 */
public class Dormitory {
    private String dormitoryId;   // 瀹胯垗鍙凤紝鍞竴鏍囪瘑
    private String building;      // 妤兼爧鍚嶇О鎴栫紪鍙?
    private int floor;            // 妤煎眰
    private int totalBeds;        // 鍙绾充汉鏁?
    private String managerName;   // 绠＄悊鍛樺鍚?
    private String managerPhone;  // 绠＄悊鍛樿仈绯荤數璇?

    // 鏋勯€犲嚱鏁?
    public Dormitory() {}

    public Dormitory(String dormitoryId, String building, int floor, int totalBeds,
                    String managerName, String managerPhone) {
        this.dormitoryId = dormitoryId;
        this.building = building;
        this.floor = floor;
        this.totalBeds = totalBeds;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
    }

    // Getter鍜孲etter鏂规硶
    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "dormitoryId='" + dormitoryId + '\'' +
                ", building='" + building + '\'' +
                ", floor=" + floor +
                ", totalBeds=" + totalBeds +
                ", managerName='" + managerName + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                '}';
    }
}

========================================
Payment.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 缂磋垂璁板綍瀹炰綋绫?
 * 鐢ㄤ簬瀛樺偍瀛︾敓鐨勪綇瀹胯垂缂磋垂淇℃伅
 */
public class Payment {
    private String paymentId;    // 缂磋垂璁板綍ID锛屽敮涓€鏍囪瘑
    private String studentId;    // 鍏宠仈瀛﹀彿
    private BigDecimal amount;   // 缂磋垂閲戦
    private Date paymentDate;    // 缂磋垂鏃ユ湡
    private String semester;     // 缂磋垂瀛︽湡
    private String status;       // 缂磋垂鐘舵€侊細鏈即/宸茬即

    // 鏋勯€犲嚱鏁?
    public Payment() {}

    public Payment(String paymentId, String studentId, BigDecimal amount,
                  Date paymentDate, String semester, String status) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.semester = semester;
        this.status = status;
    }

    // Getter鍜孲etter鏂规硶
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", semester='" + semester + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

========================================
Reminder.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

import java.util.Date;

/**
 * 鎻愰啋淇℃伅瀹炰綋绫? * 鐢ㄤ簬瀛樺偍闇€瑕佹彁閱掔殑浜嬮」锛屽鏈即璐广€佹湭澶勭悊杩濈邯绛? */
public class Reminder {
    private String reminderId;      // 鎻愰啋ID锛屽敮涓€鏍囪瘑
    private String studentId;       // 鍏宠仈瀛︾敓ID
    private String studentName;     // 瀛︾敓濮撳悕
    private String type;            // 鎻愰啋绫诲瀷锛氱即璐规彁閱掋€佽繚绾鐞嗘彁閱?    private String title;           // 鎻愰啋鏍囬
    private String content;         // 鎻愰啋鍐呭
    private String priority;        // 浼樺厛绾э細楂樸€佷腑銆佷綆
    private String status;          // 鐘舵€侊細寰呭鐞嗐€佸凡澶勭悊銆佸凡蹇界暐
    private Date createTime;        // 鍒涘缓鏃堕棿
    private Date handleTime;        // 澶勭悊鏃堕棿
    private String handler;         // 澶勭悊浜?
    // 鏋勯€犲嚱鏁?    public Reminder() {}

    public Reminder(String reminderId, String studentId, String studentName, String type,
                   String title, String content, String priority, String status,
                   Date createTime, Date handleTime, String handler) {
        this.reminderId = reminderId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.type = type;
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.status = status;
        this.createTime = createTime;
        this.handleTime = handleTime;
        this.handler = handler;
    }

    // Getter鍜孲etter鏂规硶
    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId='" + reminderId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", handleTime=" + handleTime +
                ", handler='" + handler + '\'' +
                '}';
    }
}

========================================
RepairApplication.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

import java.util.Date;

/**
 * 缁翠慨鐢宠瀹炰綋绫?
 * 鐢ㄤ簬瀛樺偍瀛︾敓鐨勫鑸嶈鏂界淮淇敵璇蜂俊鎭?
 */
public class RepairApplication {
    private String applyId;      // 鐢宠鍗曞彿锛屽敮涓€鏍囪瘑
    private String studentId;    // 鐢宠浜哄鍙?
    private String faultLocation;// 鏁呴殰浣嶇疆
    private String faultDesc;    // 鏁呴殰鎻忚堪
    private String contactPhone; // 鑱旂郴鐢佃瘽
    private Date applyTime;      // 鐢宠鏃堕棿
    private String status;       // 澶勭悊鐘舵€侊細寰呭彈鐞?宸插彈鐞?缁翠慨涓?宸插畬鎴?
    private String handler;      // 澶勭悊浜?
    private Date finishTime;     // 瀹屾垚鏃堕棿

    // 鏋勯€犲嚱鏁?
    public RepairApplication() {}

    public RepairApplication(String applyId, String studentId, String faultLocation,
                           String faultDesc, String contactPhone, Date applyTime,
                           String status, String handler, Date finishTime) {
        this.applyId = applyId;
        this.studentId = studentId;
        this.faultLocation = faultLocation;
        this.faultDesc = faultDesc;
        this.contactPhone = contactPhone;
        this.applyTime = applyTime;
        this.status = status;
        this.handler = handler;
        this.finishTime = finishTime;
    }

    // Getter鍜孲etter鏂规硶
    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFaultLocation() {
        return faultLocation;
    }

    public void setFaultLocation(String faultLocation) {
        this.faultLocation = faultLocation;
    }

    public String getFaultDesc() {
        return faultDesc;
    }

    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "RepairApplication{" +
                "applyId='" + applyId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", faultLocation='" + faultLocation + '\'' +
                ", status='" + status + '\'' +
                ", applyTime=" + applyTime +
                '}';
    }
}

========================================
Student.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

import java.util.Date;

/**
 * 瀛︾敓瀹炰綋绫?
 * 鐢ㄤ簬瀛樺偍瀛︾敓鍩烘湰淇℃伅
 */
public class Student {
    private String studentId;      // 瀛﹀彿锛屽敮涓€鏍囪瘑
    private String password;       // 瀵嗙爜
    private String name;           // 濮撳悕
    private String collegeName;    // 瀛﹂櫌鍚?
    private String className;      // 鐝骇鍙?
    private String gender;         // 鎬у埆
    private Date birthday;         // 鐢熸棩
    private String email;          // 鐢靛瓙閭
    private String phone;          // 鑱旂郴鐢佃瘽
    private String bedId;          // 鍏宠仈搴婁綅鍙?

    // 鏋勯€犲嚱鏁?
    public Student() {}

    public Student(String studentId, String password, String name, String collegeName,
                  String className, String gender, Date birthday, String email,
                  String phone, String bedId) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.collegeName = collegeName;
        this.className = className;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.bedId = bedId;
    }

    // Getter鍜孲etter鏂规硶
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", className='" + className + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", bedId='" + bedId + '\'' +
                '}';
    }
}

========================================
User.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

/**
 * 鐢ㄦ埛瀹炰綋绫?
 * 鐢ㄤ簬瀛樺偍绯荤粺鐢ㄦ埛淇℃伅
 */
public class User {
    private String userId;      // 鐢ㄦ埛鍚嶏紝鍞竴鏍囪瘑
    private String password;    // 瀵嗙爜
    private String userType;    // 鐢ㄦ埛绫诲瀷锛氱鐞嗗憳/瀛︾敓

    // 鏋勯€犲嚱鏁?
    public User() {}

    public User(String userId, String password, String userType) {
        this.userId = userId;
        this.password = password;
        this.userType = userType;
    }

    // Getter鍜孲etter鏂规硶
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}

========================================
Violation.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\entity
========================================
package org.dorm.model.entity;

import java.util.Date;

/**
 * 杩濈邯璁板綍瀹炰綋绫?
 * 鐢ㄤ簬瀛樺偍瀛︾敓鐨勮繚绾俊鎭?
 */
public class Violation {
    private String violationId;  // 杩濈邯璁板綍ID锛屽敮涓€鏍囪瘑
    private String studentId;    // 鍏宠仈瀛﹀彿
    private String description;  // 杩濈邯琛屼负鎻忚堪
    private Date violationDate;  // 杩濈邯鍙戠敓鏃ユ湡
    private String penalty;      // 澶勭綒缁撴灉

    // 鏋勯€犲嚱鏁?
    public Violation() {}

    public Violation(String violationId, String studentId, String description,
                    Date violationDate, String penalty) {
        this.violationId = violationId;
        this.studentId = studentId;
        this.description = description;
        this.violationDate = violationDate;
        this.penalty = penalty;
    }

    // Getter鍜孲etter鏂规硶
    public String getViolationId() {
        return violationId;
    }

    public void setViolationId(String violationId) {
        this.violationId = violationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getViolationDate() {
        return violationDate;
    }

    public void setViolationDate(Date violationDate) {
        this.violationDate = violationDate;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "Violation{" +
                "violationId='" + violationId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", description='" + description + '\'' +
                ", violationDate=" + violationDate +
                ", penalty='" + penalty + '\'' +
                '}';
    }
}

========================================
AccommodationServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.AccommodationDAO;
import org.dorm.model.dao.impl.AccommodationDAOImpl;
import org.dorm.model.entity.Accommodation;
import org.dorm.model.service.AccommodationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 浣忓鍒嗛厤鏈嶅姟瀹炵幇
 */
public class AccommodationServiceImpl implements AccommodationService {
    private static final Logger logger = LoggerFactory.getLogger(AccommodationServiceImpl.class);
    private AccommodationDAO accommodationDAO = new AccommodationDAOImpl();

    @Override
    public List<Accommodation> getAllAccommodations() {
        try {
            List<Accommodation> accommodations = accommodationDAO.findAll();
            logger.info("鑾峰彇鎵€鏈変綇瀹夸俊鎭垚鍔燂細{}鏉¤褰?, accommodations.size());
            return accommodations;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈変綇瀹夸俊鎭け璐?, e);
            throw new RuntimeException("鑾峰彇浣忓淇℃伅澶辫触", e);
        }
    }

    @Override
    public Accommodation getAccommodationByStudentId(String studentId) {
        try {
            Accommodation accommodation = accommodationDAO.findByStudentId(studentId);
            if (accommodation != null) {
                logger.info("鑾峰彇瀛︾敓浣忓淇℃伅鎴愬姛锛歿}", studentId);
            } else {
                logger.warn("鏈壘鍒板鐢熶綇瀹夸俊鎭細{}", studentId);
            }
            return accommodation;
        } catch (Exception e) {
            logger.error("鑾峰彇瀛︾敓浣忓淇℃伅澶辫触锛歿}", studentId, e);
            throw new RuntimeException("鑾峰彇瀛︾敓浣忓淇℃伅澶辫触", e);
        }
    }

    @Override
    public boolean assignBed(String studentId, String bedId) {
        try {
            // 楠岃瘉瀛︾敓鏄惁瀛樺湪涓旀湭鍒嗛厤搴婁綅
            Accommodation accommodation = accommodationDAO.findByStudentId(studentId);
            if (accommodation == null) {
                logger.warn("瀛︾敓涓嶅瓨鍦細{}", studentId);
                return false;
            }

            if (accommodation.getBedId() != null) {
                logger.warn("瀛︾敓宸插垎閰嶅簥浣嶏細{} -> {}", studentId, accommodation.getBedId());
                return false;
            }

            boolean result = accommodationDAO.assignBed(studentId, bedId);
            if (result) {
                logger.info("鍒嗛厤搴婁綅鎴愬姛锛氬鐢焮} -> 搴婁綅{}", studentId, bedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒嗛厤搴婁綅寮傚父锛氬鐢焮} -> 搴婁綅{}", studentId, bedId, e);
            return false;
        }
    }

    @Override
    public boolean changeBed(String studentId, String newBedId) {
        try {
            // 楠岃瘉瀛︾敓鏄惁瀛樺湪涓斿凡鍒嗛厤搴婁綅
            Accommodation accommodation = accommodationDAO.findByStudentId(studentId);
            if (accommodation == null) {
                logger.warn("瀛︾敓涓嶅瓨鍦細{}", studentId);
                return false;
            }

            if (accommodation.getBedId() == null) {
                logger.warn("瀛︾敓鏈垎閰嶅簥浣嶏細{}", studentId);
                return false;
            }

            if (accommodation.getBedId().equals(newBedId)) {
                logger.warn("鏂板簥浣嶄笌褰撳墠搴婁綅鐩稿悓锛歿}", newBedId);
                return false;
            }

            boolean result = accommodationDAO.changeBed(studentId, newBedId);
            if (result) {
                logger.info("璋冩崲搴婁綅鎴愬姛锛氬鐢焮} -> 鏂板簥浣峽}", studentId, newBedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("璋冩崲搴婁綅寮傚父锛氬鐢焮} -> 鏂板簥浣峽}", studentId, newBedId, e);
            return false;
        }
    }

    @Override
    public boolean checkout(String studentId) {
        try {
            // 楠岃瘉瀛︾敓鏄惁瀛樺湪涓斿凡鍒嗛厤搴婁綅
            Accommodation accommodation = accommodationDAO.findByStudentId(studentId);
            if (accommodation == null) {
                logger.warn("瀛︾敓涓嶅瓨鍦細{}", studentId);
                return false;
            }

            if (accommodation.getBedId() == null) {
                logger.warn("瀛︾敓鏈垎閰嶅簥浣嶏細{}", studentId);
                return false;
            }

            boolean result = accommodationDAO.checkout(studentId);
            if (result) {
                logger.info("閫€瀹挎垚鍔燂細瀛︾敓{}", studentId);
            }
            return result;
        } catch (Exception e) {
            logger.error("閫€瀹垮紓甯革細瀛︾敓{}", studentId, e);
            return false;
        }
    }

    @Override
    public List<String> getAvailableBeds() {
        try {
            List<String> availableBeds = accommodationDAO.findAvailableBeds();
            logger.info("鑾峰彇绌洪棽搴婁綅鎴愬姛锛歿}涓?, availableBeds.size());
            return availableBeds;
        } catch (Exception e) {
            logger.error("鑾峰彇绌洪棽搴婁綅澶辫触", e);
            throw new RuntimeException("鑾峰彇绌洪棽搴婁綅澶辫触", e);
        }
    }

    @Override
    public List<Accommodation> searchAccommodations(String searchText, String searchType) {
        try {
            List<Accommodation> allAccommodations = accommodationDAO.findAll();
            List<Accommodation> filteredAccommodations;

            if (searchText == null || searchText.trim().isEmpty()) {
                filteredAccommodations = allAccommodations;
            } else {
                String searchLower = searchText.toLowerCase().trim();
                filteredAccommodations = allAccommodations.stream()
                    .filter(accommodation -> {
                        switch (searchType) {
                            case "瀛﹀彿":
                                return accommodation.getStudentId() != null &&
                                       accommodation.getStudentId().toLowerCase().contains(searchLower);
                            case "濮撳悕":
                                return accommodation.getStudentName() != null &&
                                       accommodation.getStudentName().toLowerCase().contains(searchLower);
                            case "瀹胯垗":
                                return accommodation.getDormitoryId() != null &&
                                       accommodation.getDormitoryId().toLowerCase().contains(searchLower);
                            default: // "鍏ㄩ儴"
                                return (accommodation.getStudentId() != null &&
                                        accommodation.getStudentId().toLowerCase().contains(searchLower)) ||
                                       (accommodation.getStudentName() != null &&
                                        accommodation.getStudentName().toLowerCase().contains(searchLower)) ||
                                       (accommodation.getDormitoryId() != null &&
                                        accommodation.getDormitoryId().toLowerCase().contains(searchLower));
                        }
                    })
                    .collect(Collectors.toList());
            }

            logger.info("鎼滅储浣忓淇℃伅鎴愬姛锛岀被鍨嬶細{}锛屽叧閿瘝锛歿}锛岀粨鏋滐細{}鏉¤褰?,
                       searchType, searchText, filteredAccommodations.size());
            return filteredAccommodations;
        } catch (Exception e) {
            logger.error("鎼滅储浣忓淇℃伅寮傚父锛岀被鍨嬶細{}锛屽叧閿瘝锛歿}", searchType, searchText, e);
            throw new RuntimeException("鎼滅储浣忓淇℃伅澶辫触", e);
        }
    }
}

========================================
AnnouncementServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.AnnouncementDAO;
import org.dorm.model.dao.impl.AnnouncementDAOImpl;
import org.dorm.model.entity.Announcement;
import org.dorm.model.service.AnnouncementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * 鍏憡涓氬姟閫昏緫鏈嶅姟瀹炵幇绫?
 */
public class AnnouncementServiceImpl implements AnnouncementService {
    private static final Logger logger = LoggerFactory.getLogger(AnnouncementServiceImpl.class);
    private AnnouncementDAO announcementDAO = new AnnouncementDAOImpl();

    @Override
    public List<Announcement> getAllActiveAnnouncements() {
        try {
            List<Announcement> announcements = announcementDAO.findAllActive();
            logger.info("鑾峰彇婵€娲诲叕鍛婃垚鍔燂紝鍏眥}鏉?, announcements.size());
            return announcements;
        } catch (Exception e) {
            logger.error("鑾峰彇婵€娲诲叕鍛婂け璐?, e);
            throw new RuntimeException("鑾峰彇鍏憡澶辫触", e);
        }
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        try {
            List<Announcement> announcements = announcementDAO.findAll();
            logger.info("鑾峰彇鎵€鏈夊叕鍛婃垚鍔燂紝鍏眥}鏉?, announcements.size());
            return announcements;
        } catch (Exception e) {
            logger.error("鑾峰彇鎵€鏈夊叕鍛婂け璐?, e);
            throw new RuntimeException("鑾峰彇鍏憡澶辫触", e);
        }
    }

    @Override
    public Announcement getAnnouncementById(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("鑾峰彇鍏憡澶辫触锛氬叕鍛奍D涓虹┖");
            return null;
        }

        try {
            Announcement announcement = announcementDAO.findById(announcementId.trim());
            if (announcement != null) {
                logger.info("鑾峰彇鍏憡鎴愬姛锛歿}", announcementId);
            } else {
                logger.warn("鍏憡涓嶅瓨鍦細{}", announcementId);
            }
            return announcement;
        } catch (Exception e) {
            logger.error("鑾峰彇鍏憡澶辫触锛歿}", announcementId, e);
            throw new RuntimeException("鑾峰彇鍏憡澶辫触", e);
        }
    }

    @Override
    public boolean publishAnnouncement(Announcement announcement) {
        if (announcement == null) {
            logger.warn("鍙戝竷鍏憡澶辫触锛氬叕鍛婂璞′负绌?);
            return false;
        }

        if (announcement.getTitle() == null || announcement.getTitle().trim().isEmpty()) {
            logger.warn("鍙戝竷鍏憡澶辫触锛氬叕鍛婃爣棰樹负绌?);
            return false;
        }

        if (announcement.getContent() == null || announcement.getContent().trim().isEmpty()) {
            logger.warn("鍙戝竷鍏憡澶辫触锛氬叕鍛婂唴瀹逛负绌?);
            return false;
        }

        if (announcement.getPublisher() == null || announcement.getPublisher().trim().isEmpty()) {
            logger.warn("鍙戝竷鍏憡澶辫触锛氬彂甯冭€呬负绌?);
            return false;
        }

        // 璁剧疆榛樿鍊?
        if (announcement.getAnnouncementId() == null || announcement.getAnnouncementId().trim().isEmpty()) {
            announcement.setAnnouncementId(generateAnnouncementId());
        }

        if (announcement.getPublishTime() == null) {
            announcement.setPublishTime(new Date());
        }

        if (announcement.getStatus() == null || announcement.getStatus().trim().isEmpty()) {
            announcement.setStatus("active");
        }

        if (announcement.getCategory() == null || announcement.getCategory().trim().isEmpty()) {
            announcement.setCategory("general");
        }

        try {
            boolean result = announcementDAO.insert(announcement);
            if (result) {
                logger.info("鍙戝竷鍏憡鎴愬姛锛歿} - {}", announcement.getAnnouncementId(), announcement.getTitle());
            } else {
                logger.warn("鍙戝竷鍏憡澶辫触锛歿}", announcement.getTitle());
            }
            return result;
        } catch (Exception e) {
            logger.error("鍙戝竷鍏憡寮傚父", e);
            throw new RuntimeException("鍙戝竷鍏憡澶辫触", e);
        }
    }

    @Override
    public boolean updateAnnouncement(Announcement announcement) {
        if (announcement == null || announcement.getAnnouncementId() == null) {
            logger.warn("鏇存柊鍏憡澶辫触锛氬叕鍛婂璞℃垨ID涓虹┖");
            return false;
        }

        try {
            boolean result = announcementDAO.update(announcement);
            if (result) {
                logger.info("鏇存柊鍏憡鎴愬姛锛歿}", announcement.getAnnouncementId());
            } else {
                logger.warn("鏇存柊鍏憡澶辫触锛歿}", announcement.getAnnouncementId());
            }
            return result;
        } catch (Exception e) {
            logger.error("鏇存柊鍏憡寮傚父", e);
            throw new RuntimeException("鏇存柊鍏憡澶辫触", e);
        }
    }

    @Override
    public boolean deleteAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("鍒犻櫎鍏憡澶辫触锛氬叕鍛奍D涓虹┖");
            return false;
        }

        try {
            boolean result = announcementDAO.delete(announcementId.trim());
            if (result) {
                logger.info("鍒犻櫎鍏憡鎴愬姛锛歿}", announcementId);
            } else {
                logger.warn("鍒犻櫎鍏憡澶辫触锛歿}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍒犻櫎鍏憡寮傚父", e);
            throw new RuntimeException("鍒犻櫎鍏憡澶辫触", e);
        }
    }

    @Override
    public boolean activateAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("婵€娲诲叕鍛婂け璐ワ細鍏憡ID涓虹┖");
            return false;
        }

        try {
            boolean result = announcementDAO.updateStatus(announcementId.trim(), "active");
            if (result) {
                logger.info("婵€娲诲叕鍛婃垚鍔燂細{}", announcementId);
            } else {
                logger.warn("婵€娲诲叕鍛婂け璐ワ細{}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("婵€娲诲叕鍛婂紓甯?, e);
            throw new RuntimeException("婵€娲诲叕鍛婂け璐?, e);
        }
    }

    @Override
    public boolean deactivateAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("鍋滅敤鍏憡澶辫触锛氬叕鍛奍D涓虹┖");
            return false;
        }

        try {
            boolean result = announcementDAO.updateStatus(announcementId.trim(), "inactive");
            if (result) {
                logger.info("鍋滅敤鍏憡鎴愬姛锛歿}", announcementId);
            } else {
                logger.warn("鍋滅敤鍏憡澶辫触锛歿}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("鍋滅敤鍏憡寮傚父", e);
            throw new RuntimeException("鍋滅敤鍏憡澶辫触", e);
        }
    }

    @Override
    public List<Announcement> getAnnouncementsByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            logger.warn("鑾峰彇鍒嗙被鍏憡澶辫触锛氬垎绫讳负绌?);
            return announcementDAO.findAllActive();
        }

        try {
            List<Announcement> announcements = announcementDAO.findByCategory(category.trim());
            logger.info("鑾峰彇{}鍒嗙被鍏憡鎴愬姛锛屽叡{}鏉?, category, announcements.size());
            return announcements;
        } catch (Exception e) {
            logger.error("鑾峰彇鍒嗙被鍏憡澶辫触", e);
            throw new RuntimeException("鑾峰彇鍒嗙被鍏憡澶辫触", e);
        }
    }

    @Override
    public List<Announcement> getLatestAnnouncements(int limit) {
        if (limit <= 0) {
            limit = 10; // 榛樿鑾峰彇10鏉?
        }

        try {
            List<Announcement> announcements = announcementDAO.findLatest(limit);
            logger.info("鑾峰彇鏈€鏂板叕鍛婃垚鍔燂紝鍏眥}鏉?, announcements.size());
            return announcements;
        } catch (Exception e) {
            logger.error("鑾峰彇鏈€鏂板叕鍛婂け璐?, e);
            throw new RuntimeException("鑾峰彇鏈€鏂板叕鍛婂け璐?, e);
        }
    }

    @Override
    public List<Announcement> getImportantAnnouncements() {
        try {
            // 鑾峰彇閲嶈閫氱煡鍜岀揣鎬ラ€氱煡
            List<Announcement> important = announcementDAO.findByCategory("important");
            List<Announcement> emergency = announcementDAO.findByCategory("emergency");

            important.addAll(emergency);
            logger.info("鑾峰彇閲嶈鍏憡鎴愬姛锛屽叡{}鏉?, important.size());
            return important;
        } catch (Exception e) {
            logger.error("鑾峰彇閲嶈鍏憡澶辫触", e);
            throw new RuntimeException("鑾峰彇閲嶈鍏憡澶辫触", e);
        }
    }

    @Override
    public String generateAnnouncementId() {
        // 鐢熸垚鍏憡ID鏍煎紡锛欰NN + 鏃堕棿鎴?
        return "ANN" + System.currentTimeMillis();
    }
}

========================================
BedServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.BedDAO;
import org.dorm.model.dao.impl.BedDAOImpl;
import org.dorm.model.entity.Bed;
import org.dorm.model.service.BedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 搴婁綅鏈嶅姟瀹炵幇绫?
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
            // 妫€鏌ュ簥浣岻D鏄惁宸插瓨鍦?
            if (bedDAO.findByBedId(bed.getBedId()) != null) {
                logger.warn("搴婁綅ID宸插瓨鍦細{}", bed.getBedId());
                return false;
            }

            return bedDAO.addBed(bed);
        } catch (Exception e) {
            logger.error("娣诲姞搴婁綅澶辫触", e);
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

========================================
DormitoryServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.DormitoryDAO;
import org.dorm.model.dao.impl.DormitoryDAOImpl;
import org.dorm.model.entity.Dormitory;
import org.dorm.model.service.DormitoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 瀹胯垗鏈嶅姟瀹炵幇绫?
 */
public class DormitoryServiceImpl implements DormitoryService {
    private static final Logger logger = LoggerFactory.getLogger(DormitoryServiceImpl.class);

    private DormitoryDAO dormitoryDAO = new DormitoryDAOImpl();

    @Override
    public List<Dormitory> getAllDormitories() {
        return dormitoryDAO.findAll();
    }

    @Override
    public Dormitory getDormitoryById(String dormitoryId) {
        return dormitoryDAO.findByDormitoryId(dormitoryId);
    }

    @Override
    public boolean addDormitory(Dormitory dormitory) {
        try {
            // 妫€鏌ュ鑸岻D鏄惁宸插瓨鍦?
            if (dormitoryDAO.findByDormitoryId(dormitory.getDormitoryId()) != null) {
                logger.warn("瀹胯垗ID宸插瓨鍦細{}", dormitory.getDormitoryId());
                return false;
            }

            return dormitoryDAO.addDormitory(dormitory);
        } catch (Exception e) {
            logger.error("娣诲姞瀹胯垗澶辫触", e);
            return false;
        }
    }

    @Override
    public boolean updateDormitory(Dormitory dormitory) {
        return dormitoryDAO.updateDormitory(dormitory);
    }

    @Override
    public boolean deleteDormitory(String dormitoryId) {
        return dormitoryDAO.deleteDormitory(dormitoryId);
    }
}

========================================
PaymentServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.PaymentDAO;
import org.dorm.model.dao.impl.PaymentDAOImpl;
import org.dorm.model.entity.Payment;
import org.dorm.model.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 缂磋垂鏈嶅姟瀹炵幇绫?
 */
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public List<Payment> getAllPayments() {
        return paymentDAO.findAll();
    }

    @Override
    public Payment getPaymentById(String paymentId) {
        return paymentDAO.findByPaymentId(paymentId);
    }

    @Override
    public List<Payment> getPaymentsByStudentId(String studentId) {
        return paymentDAO.findByStudentId(studentId);
    }

    @Override
    public boolean addPayment(Payment payment) {
        try {
            // 妫€鏌ョ即璐瑰崟鍙锋槸鍚﹀凡瀛樺湪
            if (paymentDAO.findByPaymentId(payment.getPaymentId()) != null) {
                logger.warn("缂磋垂鍗曞彿宸插瓨鍦細{}", payment.getPaymentId());
                return false;
            }

            return paymentDAO.addPayment(payment);
        } catch (Exception e) {
            logger.error("娣诲姞缂磋垂璁板綍澶辫触", e);
            return false;
        }
    }

    @Override
    public boolean updatePayment(Payment payment) {
        return paymentDAO.updatePayment(payment);
    }

    @Override
    public boolean deletePayment(String paymentId) {
        return paymentDAO.deletePayment(paymentId);
    }
}

========================================
ReminderServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.ReminderDAO;
import org.dorm.model.dao.StudentDAO;
import org.dorm.model.dao.ViolationDAO;
import org.dorm.model.dao.PaymentDAO;
import org.dorm.model.dao.impl.ReminderDAOImpl;
import org.dorm.model.dao.impl.StudentDAOImpl;
import org.dorm.model.dao.impl.ViolationDAOImpl;
import org.dorm.model.dao.impl.PaymentDAOImpl;
import org.dorm.model.entity.Reminder;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.Violation;
import org.dorm.model.entity.Payment;
import org.dorm.model.service.ReminderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 鎻愰啋鏈嶅姟瀹炵幇绫? */
public class ReminderServiceImpl implements ReminderService {
    private static final Logger logger = LoggerFactory.getLogger(ReminderServiceImpl.class);

    private ReminderDAO reminderDAO = new ReminderDAOImpl();
    private StudentDAO studentDAO = new StudentDAOImpl();
    private ViolationDAO violationDAO = new ViolationDAOImpl();
    private PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public List<Reminder> getAllReminders() {
        return reminderDAO.findAll();
    }

    @Override
    public Reminder getReminderById(String reminderId) {
        return reminderDAO.findByReminderId(reminderId);
    }

    @Override
    public List<Reminder> getRemindersByStudentId(String studentId) {
        return reminderDAO.findByStudentId(studentId);
    }

    @Override
    public List<Reminder> getRemindersByStatus(String status) {
        return reminderDAO.findByStatus(status);
    }

    @Override
    public List<Reminder> getRemindersByType(String type) {
        return reminderDAO.findByType(type);
    }

    @Override
    public List<Reminder> getUnprocessedReminders() {
        return reminderDAO.findUnprocessedReminders();
    }

    @Override
    public boolean addReminder(Reminder reminder) {
        return reminderDAO.addReminder(reminder);
    }

    @Override
    public boolean updateReminder(Reminder reminder) {
        return reminderDAO.updateReminder(reminder);
    }

    @Override
    public boolean deleteReminder(String reminderId) {
        return reminderDAO.deleteReminder(reminderId);
    }

    @Override
    public int deleteProcessedReminders() {
        return reminderDAO.deleteProcessedReminders();
    }

    @Override
    public int checkUnpaidStudents() {
        int count = 0;
        try {
            // 鑾峰彇鎵€鏈夊鐢?            List<Student> students = studentDAO.findAll();

            for (Student student : students) {
                // 妫€鏌ヨ瀛︾敓鐨勭即璐硅褰?                List<Payment> payments = paymentDAO.findByStudentId(student.getStudentId());

                boolean hasUnpaid = false;
                for (Payment payment : payments) {
                    if ("鏈即".equals(payment.getStatus())) {
                        hasUnpaid = true;
                        break;
                    }
                }

                if (hasUnpaid) {
                    // 妫€鏌ユ槸鍚﹀凡鏈夋彁閱?                    List<Reminder> existingReminders = reminderDAO.findByStudentId(student.getStudentId());
                    boolean alreadyExists = false;
                    for (Reminder reminder : existingReminders) {
                        if ("缂磋垂鎻愰啋".equals(reminder.getType()) && "寰呭鐞?.equals(reminder.getStatus())) {
                            alreadyExists = true;
                            break;
                        }
                    }

                    if (!alreadyExists) {
                        // 鍒涘缓鏂扮殑缂磋垂鎻愰啋
                        Reminder reminder = new Reminder();
                        reminder.setReminderId("REMINDER_" + UUID.randomUUID().toString().substring(0, 8));
                        reminder.setStudentId(student.getStudentId());
                        reminder.setStudentName(student.getName());
                        reminder.setType("缂磋垂鎻愰啋");
                        reminder.setTitle("浣忓璐规湭缂存彁閱?);
                        reminder.setContent("瀛︾敓 " + student.getName() + " (" + student.getStudentId() + ") 瀛樺湪鏈即绾崇殑浣忓璐圭敤锛岃鍙婃椂澶勭悊銆?);
                        reminder.setPriority("楂?);
                        reminder.setStatus("寰呭鐞?);
                        reminder.setCreateTime(new Date());

                        if (addReminder(reminder)) {
                            count++;
                            logger.info("涓哄鐢?{} 鐢熸垚缂磋垂鎻愰啋", student.getStudentId());
                        }
                    }
                }
            }

            logger.info("缂磋垂鎻愰啋妫€鏌ュ畬鎴愶紝鍏辩敓鎴?{} 鏉℃柊鎻愰啋", count);
        } catch (Exception e) {
            logger.error("妫€鏌ユ湭缂磋垂瀛︾敓澶辫触", e);
        }

        return count;
    }

    @Override
    public int checkUnprocessedViolations() {
        int count = 0;
        try {
            // 鑾峰彇鎵€鏈夎繚绾褰?            List<Violation> violations = violationDAO.findAll();

            for (Violation violation : violations) {
                // 妫€鏌ユ槸鍚﹂渶瑕佸鐞嗭紙鍋囪鏈夊鐞嗙姸鎬侊紝杩欓噷绠€鍖栧垽鏂級
                // 鍦ㄥ疄闄呭簲鐢ㄤ腑锛屽彲浠ユ牴鎹叿浣撲笟鍔¤鍒欏垽鏂繚绾槸鍚﹂渶瑕佸鐞?                if (violation.getPenalty() == null || violation.getPenalty().trim().isEmpty()) {
                    // 妫€鏌ユ槸鍚﹀凡鏈夋彁閱?                    List<Reminder> existingReminders = reminderDAO.findByStudentId(violation.getStudentId());
                    boolean alreadyExists = false;
                    for (Reminder reminder : existingReminders) {
                        if ("杩濈邯澶勭悊鎻愰啋".equals(reminder.getType()) && "寰呭鐞?.equals(reminder.getStatus())) {
                            alreadyExists = true;
                            break;
                        }
                    }

                    if (!alreadyExists) {
                        // 鍒涘缓鏂扮殑杩濈邯澶勭悊鎻愰啋
                        Reminder reminder = new Reminder();
                        reminder.setReminderId("REMINDER_" + UUID.randomUUID().toString().substring(0, 8));
                        reminder.setStudentId(violation.getStudentId());
                        reminder.setStudentName(getStudentNameById(violation.getStudentId()));
                        reminder.setType("杩濈邯澶勭悊鎻愰啋");
                        reminder.setTitle("杩濈邯璁板綍澶勭悊鎻愰啋");
                        reminder.setContent("瀛︾敓 " + getStudentNameById(violation.getStudentId()) + " (" + violation.getStudentId() + ") 瀛樺湪鏈鐞嗙殑杩濈邯璁板綍锛? +
                                          violation.getDescription() + "锛岃鍙婃椂澶勭悊銆?);
                        reminder.setPriority("涓?);
                        reminder.setStatus("寰呭鐞?);
                        reminder.setCreateTime(new Date());

                        if (addReminder(reminder)) {
                            count++;
                            logger.info("涓哄鐢?{} 鐢熸垚杩濈邯澶勭悊鎻愰啋", violation.getStudentId());
                        }
                    }
                }
            }

            logger.info("杩濈邯澶勭悊鎻愰啋妫€鏌ュ畬鎴愶紝鍏辩敓鎴?{} 鏉℃柊鎻愰啋", count);
        } catch (Exception e) {
            logger.error("妫€鏌ユ湭澶勭悊杩濈邯璁板綍澶辫触", e);
        }

        return count;
    }

    @Override
    public int refreshAllReminders() {
        try {
            // 鍒犻櫎鎵€鏈夋棫鎻愰啋
            int deletedCount = deleteProcessedReminders();
            logger.info("鍒犻櫎浜?{} 鏉″凡澶勭悊鐨勬彁閱?, deletedCount);

            // 閲嶆柊妫€鏌ュ苟鐢熸垚鎻愰啋
            int paymentReminders = checkUnpaidStudents();
            int violationReminders = checkUnprocessedViolations();

            int total = paymentReminders + violationReminders;
            logger.info("鎻愰啋鍒锋柊瀹屾垚锛屽叡鐢熸垚 {} 鏉℃柊鎻愰啋", total);

            return total;
        } catch (Exception e) {
            logger.error("鍒锋柊鎻愰啋澶辫触", e);
            return 0;
        }
    }

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇瀛︾敓濮撳悕
     */
    private String getStudentNameById(String studentId) {
        try {
            Student student = studentDAO.findByStudentId(studentId);
            return student != null ? student.getName() : "鏈煡瀛︾敓";
        } catch (Exception e) {
            logger.error("鑾峰彇瀛︾敓濮撳悕澶辫触锛歿}", studentId, e);
            return "鏈煡瀛︾敓";
        }
    }
}

========================================
RepairApplicationServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.RepairApplicationDAO;
import org.dorm.model.dao.impl.RepairApplicationDAOImpl;
import org.dorm.model.entity.RepairApplication;
import org.dorm.model.service.RepairApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 缁翠慨鐢宠涓氬姟閫昏緫鏈嶅姟瀹炵幇绫?
 */
public class RepairApplicationServiceImpl implements RepairApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(RepairApplicationServiceImpl.class);
    private RepairApplicationDAO repairDAO = new RepairApplicationDAOImpl();

    @Override
    public boolean submitRepairApplication(RepairApplication application) {
        if (application == null) {
            logger.warn("鎻愪氦缁翠慨鐢宠澶辫触锛氱敵璇蜂俊鎭负绌?);
            return false;
        }

        if (application.getStudentId() == null || application.getFaultLocation() == null ||
            application.getFaultDesc() == null || application.getContactPhone() == null) {
            logger.warn("鎻愪氦缁翠慨鐢宠澶辫触锛氬繀濉俊鎭笉瀹屾暣");
            return false;
        }

        // 鐢熸垚鐢宠鍗曞彿
        String applyId = generateApplyId(application.getStudentId());
        application.setApplyId(applyId);

        // 璁剧疆鐢宠鏃堕棿鍜屽垵濮嬬姸鎬?
        application.setApplyTime(new Date());
        application.setStatus("pending");

        boolean result = repairDAO.addRepairApplication(application);
        if (result) {
            logger.info("缁翠慨鐢宠鎻愪氦鎴愬姛锛歿} - {}", applyId, application.getStudentId());
        } else {
            logger.error("缁翠慨鐢宠鎻愪氦澶辫触锛歿}", application.getStudentId());
        }
        return result;
    }

    @Override
    public List<RepairApplication> getStudentApplications(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            logger.warn("鑾峰彇瀛︾敓缁翠慨鐢宠澶辫触锛氬鐢熷鍙蜂负绌?);
            return List.of();
        }

        List<RepairApplication> applications = repairDAO.findByStudentId(studentId.trim());
        logger.info("鑾峰彇瀛︾敓缁翠慨鐢宠鎴愬姛锛歿} - {}鏉¤褰?, studentId, applications.size());
        return applications;
    }

    @Override
    public List<RepairApplication> getAllApplications() {
        List<RepairApplication> applications = repairDAO.findAll();
        logger.info("鑾峰彇鎵€鏈夌淮淇敵璇锋垚鍔燂細{}鏉¤褰?, applications.size());
        return applications;
    }

    @Override
    public List<RepairApplication> getPendingApplications() {
        List<RepairApplication> applications = repairDAO.findByStatus("pending");
        logger.info("鑾峰彇寰呭鐞嗙淮淇敵璇锋垚鍔燂細{}鏉¤褰?, applications.size());
        return applications;
    }

    @Override
    public boolean updateApplicationStatus(String applyId, String status, String handler) {
        if (applyId == null || status == null) {
            logger.warn("鏇存柊缁翠慨鐢宠鐘舵€佸け璐ワ細鍙傛暟涓嶅畬鏁?);
            return false;
        }

        // 楠岃瘉鐘舵€佸€?
        if (!isValidStatus(status)) {
            logger.warn("鏇存柊缁翠慨鐢宠鐘舵€佸け璐ワ細鏃犳晥鐨勭姸鎬佸€?- {}", status);
            return false;
        }

        boolean result = repairDAO.updateStatus(applyId, status, handler);
        if (result) {
            logger.info("缁翠慨鐢宠鐘舵€佹洿鏂版垚鍔燂細{} -> {}", applyId, status);
        } else {
            logger.error("缁翠慨鐢宠鐘舵€佹洿鏂板け璐ワ細{}", applyId);
        }
        return result;
    }

    @Override
    public boolean completeRepairApplication(String applyId) {
        if (applyId == null || applyId.trim().isEmpty()) {
            logger.warn("瀹屾垚缁翠慨鐢宠澶辫触锛氱敵璇峰崟鍙蜂负绌?);
            return false;
        }

        boolean result = repairDAO.completeRepair(applyId.trim(), new Date());
        if (result) {
            logger.info("缁翠慨鐢宠瀹屾垚锛歿}", applyId);
        } else {
            logger.error("缁翠慨鐢宠瀹屾垚澶辫触锛歿}", applyId);
        }
        return result;
    }

    @Override
    public String generateApplyId(String studentId) {
        // 鐢熸垚鏍煎紡锛歐X + 骞存湀鏃?+ 瀛︾敓瀛﹀彿鍚?浣?+ 闅忔満鏁?
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String studentSuffix = studentId.length() >= 4 ?
            studentId.substring(studentId.length() - 4) :
            studentId;
        String randomStr = UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        return "WX" + dateStr + studentSuffix + randomStr;
    }

    /**
     * 楠岃瘉鐘舵€佸€兼槸鍚︽湁鏁?
     */
    private boolean isValidStatus(String status) {
        return status.equals("pending") || status.equals("accepted") ||
               status.equals("repairing") || status.equals("completed");
    }
}

========================================
StudentServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.StudentDAO;
import org.dorm.model.dao.impl.StudentDAOImpl;
import org.dorm.model.entity.Student;
import org.dorm.model.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 瀛︾敓鏈嶅姟瀹炵幇绫?
 */
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    @Override
    public Student getStudentById(String studentId) {
        return studentDAO.findByStudentId(studentId);
    }

    @Override
    public boolean addStudent(Student student) {
        try {
            // 妫€鏌ュ鍙锋槸鍚﹀凡瀛樺湪
            if (studentDAO.findByStudentId(student.getStudentId()) != null) {
                logger.warn("瀛﹀彿宸插瓨鍦細{}", student.getStudentId());
                return false;
            }

            return studentDAO.addStudent(student);
        } catch (Exception e) {
            logger.error("娣诲姞瀛︾敓澶辫触", e);
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(String studentId) {
        return studentDAO.deleteStudent(studentId);
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        return studentDAO.findByClass(name); // 鏆傛椂鐢ㄧ彮绾ф煡鎵句唬鏇?
    }

    @Override
    public int getStudentCount() {
        return studentDAO.findAll().size(); // 鏆傛椂鐢ㄥ垪琛ㄥぇ灏忎唬鏇?
    }
}

========================================
UserServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.UserDAO;
import org.dorm.model.dao.impl.UserDAOImpl;
import org.dorm.model.entity.User;
import org.dorm.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 鐢ㄦ埛涓氬姟閫昏緫鏈嶅姟瀹炵幇绫?
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User login(String userId, String password) {
        if (userId == null || userId.trim().isEmpty()) {
            logger.warn("鐧诲綍澶辫触锛氱敤鎴峰悕涓虹┖");
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            logger.warn("鐧诲綍澶辫触锛氬瘑鐮佷负绌?);
            return null;
        }

        User user = userDAO.login(userId.trim(), password.trim());
        if (user != null) {
            logger.info("鐢ㄦ埛鐧诲綍鎴愬姛锛歿}", userId);
        } else {
            logger.warn("鐢ㄦ埛鐧诲綍澶辫触锛氱敤鎴峰悕鎴栧瘑鐮侀敊璇?- {}", userId);
        }
        return user;
    }

    @Override
    public boolean register(User user) {
        if (user == null || user.getUserId() == null || user.getUserId().trim().isEmpty()) {
            logger.warn("娉ㄥ唽澶辫触锛氱敤鎴蜂俊鎭笉瀹屾暣");
            return false;
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            logger.warn("娉ㄥ唽澶辫触锛氬瘑鐮佷笉鑳戒负绌?);
            return false;
        }

        if (isUserExists(user.getUserId())) {
            logger.warn("娉ㄥ唽澶辫触锛氱敤鎴峰悕宸插瓨鍦?- {}", user.getUserId());
            return false;
        }

        boolean result = userDAO.addUser(user);
        if (result) {
            logger.info("鐢ㄦ埛娉ㄥ唽鎴愬姛锛歿}", user.getUserId());
        } else {
            logger.error("鐢ㄦ埛娉ㄥ唽澶辫触锛歿}", user.getUserId());
        }
        return result;
    }

    @Override
    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        if (userId == null || oldPassword == null || newPassword == null) {
            logger.warn("淇敼瀵嗙爜澶辫触锛氬弬鏁颁笉瀹屾暣");
            return false;
        }

        if (newPassword.trim().isEmpty()) {
            logger.warn("淇敼瀵嗙爜澶辫触锛氭柊瀵嗙爜涓嶈兘涓虹┖");
            return false;
        }

        // 楠岃瘉鏃у瘑鐮?
        User user = userDAO.login(userId, oldPassword);
        if (user == null) {
            logger.warn("淇敼瀵嗙爜澶辫触锛氭棫瀵嗙爜閿欒 - {}", userId);
            return false;
        }

        // 鏇存柊瀵嗙爜
        user.setPassword(newPassword.trim());
        boolean result = userDAO.updateUser(user);
        if (result) {
            logger.info("瀵嗙爜淇敼鎴愬姛锛歿}", userId);
        } else {
            logger.error("瀵嗙爜淇敼澶辫触锛歿}", userId);
        }
        return result;
    }

    @Override
    public boolean isUserExists(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return false;
        }
        return userDAO.findByUserId(userId.trim()) != null;
    }

    @Override
    public User getUserById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return null;
        }
        return userDAO.findByUserId(userId.trim());
    }
}

========================================
ViolationServiceImpl.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service\impl
========================================
package org.dorm.model.service.impl;

import org.dorm.model.dao.ViolationDAO;
import org.dorm.model.dao.impl.ViolationDAOImpl;
import org.dorm.model.entity.Violation;
import org.dorm.model.service.ViolationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 杩濈邯鏈嶅姟瀹炵幇绫?
 */
public class ViolationServiceImpl implements ViolationService {
    private static final Logger logger = LoggerFactory.getLogger(ViolationServiceImpl.class);
    private ViolationDAO violationDAO = new ViolationDAOImpl();

    @Override
    public List<Violation> getAllViolations() {
        return violationDAO.findAll();
    }

    @Override
    public Violation getViolationById(String violationId) {
        return violationDAO.findByViolationId(violationId);
    }

    @Override
    public List<Violation> getViolationsByStudentId(String studentId) {
        return violationDAO.findByStudentId(studentId);
    }

    @Override
    public boolean addViolation(Violation violation) {
        try {
            // 妫€鏌ヨ繚绾崟鍙锋槸鍚﹀凡瀛樺湪
            if (violationDAO.findByViolationId(violation.getViolationId()) != null) {
                logger.warn("杩濈邯鍗曞彿宸插瓨鍦細{}", violation.getViolationId());
                return false;
            }

            return violationDAO.addViolation(violation);
        } catch (Exception e) {
            logger.error("娣诲姞杩濈邯璁板綍澶辫触", e);
            return false;
        }
    }

    @Override
    public boolean updateViolation(Violation violation) {
        return violationDAO.updateViolation(violation);
    }

    @Override
    public boolean deleteViolation(String violationId) {
        return violationDAO.deleteViolation(violationId);
    }
}

========================================
AccommodationService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.Accommodation;
import java.util.List;

/**
 * 浣忓鍒嗛厤鏈嶅姟鎺ュ彛
 */
public interface AccommodationService {
    /**
     * 鑾峰彇鎵€鏈変綇瀹夸俊鎭?
     * @return 浣忓淇℃伅鍒楄〃
     */
    List<Accommodation> getAllAccommodations();

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇浣忓淇℃伅
     * @param studentId 瀛︾敓ID
     * @return 浣忓淇℃伅
     */
    Accommodation getAccommodationByStudentId(String studentId);

    /**
     * 鍒嗛厤搴婁綅
     * @param studentId 瀛︾敓ID
     * @param bedId 搴婁綅ID
     * @return 鏄惁鎴愬姛
     */
    boolean assignBed(String studentId, String bedId);

    /**
     * 璋冩崲搴婁綅
     * @param studentId 瀛︾敓ID
     * @param newBedId 鏂板簥浣岻D
     * @return 鏄惁鎴愬姛
     */
    boolean changeBed(String studentId, String newBedId);

    /**
     * 閫€瀹?
     * @param studentId 瀛︾敓ID
     * @return 鏄惁鎴愬姛
     */
    boolean checkout(String studentId);

    /**
     * 鑾峰彇绌洪棽搴婁綅鍒楄〃
     * @return 绌洪棽搴婁綅ID鍒楄〃
     */
    List<String> getAvailableBeds();

    /**
     * 鏍规嵁鏉′欢鎼滅储浣忓淇℃伅
     * @param searchText 鎼滅储鏂囨湰
     * @param searchType 鎼滅储绫诲瀷
     * @return 浣忓淇℃伅鍒楄〃
     */
    List<Accommodation> searchAccommodations(String searchText, String searchType);
}

========================================
AnnouncementService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.Announcement;
import java.util.List;

/**
 * 鍏憡涓氬姟閫昏緫鏈嶅姟鎺ュ彛
 */
public interface AnnouncementService {

    /**
     * 鑾峰彇鎵€鏈夋縺娲荤姸鎬佺殑鍏憡
     * @return 鍏憡鍒楄〃
     */
    List<Announcement> getAllActiveAnnouncements();

    /**
     * 鑾峰彇鎵€鏈夊叕鍛婏紙绠＄悊鍛樻煡鐪嬶級
     * @return 鍏憡鍒楄〃
     */
    List<Announcement> getAllAnnouncements();

    /**
     * 鏍规嵁ID鑾峰彇鍏憡
     * @param announcementId 鍏憡ID
     * @return 鍏憡瀵硅薄
     */
    Announcement getAnnouncementById(String announcementId);

    /**
     * 鍙戝竷鏂板叕鍛?
     * @param announcement 鍏憡瀵硅薄
     * @return 鏄惁鎴愬姛
     */
    boolean publishAnnouncement(Announcement announcement);

    /**
     * 鏇存柊鍏憡
     * @param announcement 鍏憡瀵硅薄
     * @return 鏄惁鎴愬姛
     */
    boolean updateAnnouncement(Announcement announcement);

    /**
     * 鍒犻櫎鍏憡
     * @param announcementId 鍏憡ID
     * @return 鏄惁鎴愬姛
     */
    boolean deleteAnnouncement(String announcementId);

    /**
     * 婵€娲诲叕鍛?
     * @param announcementId 鍏憡ID
     * @return 鏄惁鎴愬姛
     */
    boolean activateAnnouncement(String announcementId);

    /**
     * 鍋滅敤鍏憡
     * @param announcementId 鍏憡ID
     * @return 鏄惁鎴愬姛
     */
    boolean deactivateAnnouncement(String announcementId);

    /**
     * 鏍规嵁鍒嗙被鑾峰彇鍏憡
     * @param category 鍒嗙被
     * @return 鍏憡鍒楄〃
     */
    List<Announcement> getAnnouncementsByCategory(String category);

    /**
     * 鑾峰彇鏈€鏂扮殑N鏉″叕鍛?
     * @param limit 鏁伴噺闄愬埗
     * @return 鍏憡鍒楄〃
     */
    List<Announcement> getLatestAnnouncements(int limit);

    /**
     * 鑾峰彇閲嶈鍏憡锛堢揣鎬ラ€氱煡鍜岄噸瑕侀€氱煡锛?
     * @return 鍏憡鍒楄〃
     */
    List<Announcement> getImportantAnnouncements();

    /**
     * 鐢熸垚鍏憡ID
     * @return 鏂板叕鍛奍D
     */
    String generateAnnouncementId();
}

========================================
BedService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.Bed;

import java.util.List;

/**
 * 搴婁綅鏈嶅姟鎺ュ彛
 */
public interface BedService {
    /**
     * 鑾峰彇鎵€鏈夊簥浣?
     * @return 搴婁綅鍒楄〃
     */
    List<Bed> getAllBeds();

    /**
     * 鏍规嵁搴婁綅ID鑾峰彇搴婁綅
     * @param bedId 搴婁綅ID
     * @return 搴婁綅
     */
    Bed getBedById(String bedId);

    /**
     * 鑾峰彇鍙敤搴婁綅
     * @return 鍙敤搴婁綅鍒楄〃
     */
    List<Bed> getAvailableBeds();

    /**
     * 鏍规嵁瀹胯垗ID鑾峰彇搴婁綅
     * @param dormId 瀹胯垗ID
     * @return 搴婁綅鍒楄〃
     */
    List<Bed> getBedsByDormId(String dormId);

    /**
     * 娣诲姞搴婁綅
     * @param bed 搴婁綅
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addBed(Bed bed);

    /**
     * 鏇存柊搴婁綅
     * @param bed 搴婁綅
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateBed(Bed bed);

    /**
     * 鍒犻櫎搴婁綅
     * @param bedId 搴婁綅ID
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteBed(String bedId);
}

========================================
DormitoryService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.Dormitory;

import java.util.List;

/**
 * 瀹胯垗鏈嶅姟鎺ュ彛
 */
public interface DormitoryService {

    /**
     * 鑾峰彇鎵€鏈夊鑸?
     * @return 瀹胯垗鍒楄〃
     */
    List<Dormitory> getAllDormitories();

    /**
     * 鏍规嵁瀹胯垗ID鑾峰彇瀹胯垗淇℃伅
     * @param dormitoryId 瀹胯垗ID
     * @return 瀹胯垗淇℃伅
     */
    Dormitory getDormitoryById(String dormitoryId);

    /**
     * 娣诲姞瀹胯垗
     * @param dormitory 瀹胯垗淇℃伅
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addDormitory(Dormitory dormitory);

    /**
     * 鏇存柊瀹胯垗淇℃伅
     * @param dormitory 瀹胯垗淇℃伅
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateDormitory(Dormitory dormitory);

    /**
     * 鍒犻櫎瀹胯垗
     * @param dormitoryId 瀹胯垗ID
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteDormitory(String dormitoryId);
}

========================================
PaymentService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.Payment;

import java.util.List;

/**
 * 缂磋垂鏈嶅姟鎺ュ彛
 */
public interface PaymentService {
    /**
     * 鑾峰彇鎵€鏈夌即璐硅褰?
     * @return 缂磋垂璁板綍鍒楄〃
     */
    List<Payment> getAllPayments();

    /**
     * 鏍规嵁缂磋垂鍗曞彿鑾峰彇缂磋垂璁板綍
     * @param paymentId 缂磋垂鍗曞彿
     * @return 缂磋垂璁板綍
     */
    Payment getPaymentById(String paymentId);

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇缂磋垂璁板綍
     * @param studentId 瀛︾敓ID
     * @return 缂磋垂璁板綍鍒楄〃
     */
    List<Payment> getPaymentsByStudentId(String studentId);

    /**
     * 娣诲姞缂磋垂璁板綍
     * @param payment 缂磋垂璁板綍
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addPayment(Payment payment);

    /**
     * 鏇存柊缂磋垂璁板綍
     * @param payment 缂磋垂璁板綍
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updatePayment(Payment payment);

    /**
     * 鍒犻櫎缂磋垂璁板綍
     * @param paymentId 缂磋垂鍗曞彿
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deletePayment(String paymentId);
}

========================================
ReminderService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.Reminder;

import java.util.List;

/**
 * 鎻愰啋鏈嶅姟鎺ュ彛
 */
public interface ReminderService {

    /**
     * 鑾峰彇鎵€鏈夋彁閱?     * @return 鎻愰啋鍒楄〃
     */
    List<Reminder> getAllReminders();

    /**
     * 鏍规嵁鎻愰啋ID鑾峰彇鎻愰啋
     * @param reminderId 鎻愰啋ID
     * @return 鎻愰啋淇℃伅
     */
    Reminder getReminderById(String reminderId);

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇鎻愰啋鍒楄〃
     * @param studentId 瀛︾敓ID
     * @return 鎻愰啋鍒楄〃
     */
    List<Reminder> getRemindersByStudentId(String studentId);

    /**
     * 鏍规嵁鐘舵€佽幏鍙栨彁閱掑垪琛?     * @param status 鐘舵€?     * @return 鎻愰啋鍒楄〃
     */
    List<Reminder> getRemindersByStatus(String status);

    /**
     * 鏍规嵁绫诲瀷鑾峰彇鎻愰啋鍒楄〃
     * @param type 绫诲瀷
     * @return 鎻愰啋鍒楄〃
     */
    List<Reminder> getRemindersByType(String type);

    /**
     * 鑾峰彇鏈鐞嗙殑鎻愰啋
     * @return 鏈鐞嗘彁閱掑垪琛?     */
    List<Reminder> getUnprocessedReminders();

    /**
     * 娣诲姞鎻愰啋
     * @param reminder 鎻愰啋淇℃伅
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addReminder(Reminder reminder);

    /**
     * 鏇存柊鎻愰啋
     * @param reminder 鎻愰啋淇℃伅
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateReminder(Reminder reminder);

    /**
     * 鍒犻櫎鎻愰啋
     * @param reminderId 鎻愰啋ID
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteReminder(String reminderId);

    /**
     * 鎵归噺鍒犻櫎宸插鐞嗙殑鎻愰啋
     * @return 鍒犻櫎鐨勮褰曟暟
     */
    int deleteProcessedReminders();

    /**
     * 妫€鏌ユ湭缂磋垂瀛︾敓骞剁敓鎴愭彁閱?     * @return 鐢熸垚鐨勬彁閱掓暟閲?     */
    int checkUnpaidStudents();

    /**
     * 妫€鏌ユ湭澶勭悊杩濈邯璁板綍骞剁敓鎴愭彁閱?     * @return 鐢熸垚鐨勬彁閱掓暟閲?     */
    int checkUnprocessedViolations();

    /**
     * 鍒锋柊鎵€鏈夋彁閱掞紙娓呴櫎鏃ф彁閱掞紝閲嶆柊鐢熸垚锛?     * @return 鏇存柊鐨勬彁閱掓€绘暟
     */
    int refreshAllReminders();
}

========================================
RepairApplicationService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.RepairApplication;
import java.util.List;

/**
 * 缁翠慨鐢宠涓氬姟閫昏緫鏈嶅姟鎺ュ彛
 */
public interface RepairApplicationService {
    /**
     * 鎻愪氦缁翠慨鐢宠
     * @param application 缁翠慨鐢宠瀵硅薄
     * @return 鏄惁鎻愪氦鎴愬姛
     */
    boolean submitRepairApplication(RepairApplication application);

    /**
     * 鑾峰彇瀛︾敓鑷繁鐨勭淮淇敵璇?
     * @param studentId 瀛︾敓瀛﹀彿
     * @return 缁翠慨鐢宠鍒楄〃
     */
    List<RepairApplication> getStudentApplications(String studentId);

    /**
     * 鑾峰彇鎵€鏈夌淮淇敵璇凤紙绠＄悊鍛樺姛鑳斤級
     * @return 缁翠慨鐢宠鍒楄〃
     */
    List<RepairApplication> getAllApplications();

    /**
     * 鑾峰彇寰呭鐞嗙殑缁翠慨鐢宠
     * @return 缁翠慨鐢宠鍒楄〃
     */
    List<RepairApplication> getPendingApplications();

    /**
     * 鏇存柊缁翠慨鐢宠鐘舵€?
     * @param applyId 鐢宠鍗曞彿
     * @param status 鏂扮姸鎬?
     * @param handler 澶勭悊浜?
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateApplicationStatus(String applyId, String status, String handler);

    /**
     * 瀹屾垚缁翠慨鐢宠
     * @param applyId 鐢宠鍗曞彿
     * @return 鏄惁瀹屾垚鎴愬姛
     */
    boolean completeRepairApplication(String applyId);

    /**
     * 鐢熸垚鐢宠鍗曞彿
     * @param studentId 瀛︾敓瀛﹀彿
     * @return 鐢宠鍗曞彿
     */
    String generateApplyId(String studentId);
}

========================================
StudentService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.Student;

import java.util.List;

/**
 * 瀛︾敓鏈嶅姟鎺ュ彛
 */
public interface StudentService {

    /**
     * 鑾峰彇鎵€鏈夊鐢?
     * @return 瀛︾敓鍒楄〃
     */
    List<Student> getAllStudents();

    /**
     * 鏍规嵁瀛﹀彿鏌ユ壘瀛︾敓
     * @param studentId 瀛﹀彿
     * @return 瀛︾敓淇℃伅
     */
    Student getStudentById(String studentId);

    /**
     * 娣诲姞鏂板鐢?
     * @param student 瀛︾敓淇℃伅
     * @return 鏄惁鎴愬姛
     */
    boolean addStudent(Student student);

    /**
     * 鏇存柊瀛︾敓淇℃伅
     * @param student 瀛︾敓淇℃伅
     * @return 鏄惁鎴愬姛
     */
    boolean updateStudent(Student student);

    /**
     * 鍒犻櫎瀛︾敓
     * @param studentId 瀛﹀彿
     * @return 鏄惁鎴愬姛
     */
    boolean deleteStudent(String studentId);

    /**
     * 鏍规嵁濮撳悕鎼滅储瀛︾敓
     * @param name 瀛︾敓濮撳悕
     * @return 瀛︾敓鍒楄〃
     */
    List<Student> searchStudentsByName(String name);

    /**
     * 鑾峰彇瀛︾敓鎬绘暟
     * @return 瀛︾敓鏁伴噺
     */
    int getStudentCount();
}

========================================
UserService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.User;

/**
 * 鐢ㄦ埛涓氬姟閫昏緫鏈嶅姟鎺ュ彛
 */
public interface UserService {
    /**
     * 鐢ㄦ埛鐧诲綍楠岃瘉
     * @param userId 鐢ㄦ埛鍚?
     * @param password 瀵嗙爜
     * @return 鐢ㄦ埛瀵硅薄锛屽鏋滅櫥褰曞け璐ヨ繑鍥瀗ull
     */
    User login(String userId, String password);

    /**
     * 娉ㄥ唽鏂扮敤鎴?
     * @param user 鐢ㄦ埛瀵硅薄
     * @return 鏄惁娉ㄥ唽鎴愬姛
     */
    boolean register(User user);

    /**
     * 淇敼瀵嗙爜
     * @param userId 鐢ㄦ埛鍚?
     * @param oldPassword 鏃у瘑鐮?
     * @param newPassword 鏂板瘑鐮?
     * @return 鏄惁淇敼鎴愬姛
     */
    boolean changePassword(String userId, String oldPassword, String newPassword);

    /**
     * 妫€鏌ョ敤鎴峰悕鏄惁瀛樺湪
     * @param userId 鐢ㄦ埛鍚?
     * @return 鏄惁瀛樺湪
     */
    boolean isUserExists(String userId);

    /**
     * 鏍规嵁鐢ㄦ埛鍚嶈幏鍙栫敤鎴蜂俊鎭?
     * @param userId 鐢ㄦ埛鍚?
     * @return 鐢ㄦ埛瀵硅薄
     */
    User getUserById(String userId);
}

========================================
ViolationService.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\model\service
========================================
package org.dorm.model.service;

import org.dorm.model.entity.Violation;

import java.util.List;

/**
 * 杩濈邯鏈嶅姟鎺ュ彛
 */
public interface ViolationService {
    /**
     * 鑾峰彇鎵€鏈夎繚绾褰?
     * @return 杩濈邯璁板綍鍒楄〃
     */
    List<Violation> getAllViolations();

    /**
     * 鏍规嵁杩濈邯鍗曞彿鑾峰彇杩濈邯璁板綍
     * @param violationId 杩濈邯鍗曞彿
     * @return 杩濈邯璁板綍
     */
    Violation getViolationById(String violationId);

    /**
     * 鏍规嵁瀛︾敓ID鑾峰彇杩濈邯璁板綍
     * @param studentId 瀛︾敓ID
     * @return 杩濈邯璁板綍鍒楄〃
     */
    List<Violation> getViolationsByStudentId(String studentId);

    /**
     * 娣诲姞杩濈邯璁板綍
     * @param violation 杩濈邯璁板綍
     * @return 鏄惁娣诲姞鎴愬姛
     */
    boolean addViolation(Violation violation);

    /**
     * 鏇存柊杩濈邯璁板綍
     * @param violation 杩濈邯璁板綍
     * @return 鏄惁鏇存柊鎴愬姛
     */
    boolean updateViolation(Violation violation);

    /**
     * 鍒犻櫎杩濈邯璁板綍
     * @param violationId 杩濈邯鍗曞彿
     * @return 鏄惁鍒犻櫎鎴愬姛
     */
    boolean deleteViolation(String violationId);
}

========================================
DatabaseUtil.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\util
========================================
// 澹版槑褰撳墠绫绘墍鍦ㄧ殑鍖呰矾寰勶紝util鍖呯敤浜庡瓨鏀鹃」鐩殑宸ュ叿绫?
package org.dorm.util;

// 瀵煎叆java.sql鍖呬笅鐨凜onnection鎺ュ彛锛岃鎺ュ彛鐢ㄤ簬琛ㄧずJava绋嬪簭涓庢暟鎹簱鐨勭墿鐞嗚繛鎺?
import java.sql.Connection;
// 瀵煎叆java.sql鍖呬笅鐨凞riverManager绫伙紝璇ョ被鏄疛DBC鐨勯┍鍔ㄧ鐞嗙被锛岀敤浜庡姞杞介┍鍔ㄥ苟鑾峰彇鏁版嵁搴撹繛鎺?
import java.sql.DriverManager;
// 瀵煎叆java.sql鍖呬笅鐨凷QLException寮傚父绫伙紝JDBC鐩稿叧鏁版嵁搴撴搷浣滅殑寮傚父缁熶竴鎶涘嚭璇ュ紓甯?
import java.sql.SQLException;
// 瀵煎叆SLF4J鏃ュ織妗嗘灦鐨凩ogger鎺ュ彛锛岀敤浜庢棩蹇楁墦鍗?
import org.slf4j.Logger;
// 瀵煎叆SLF4J鏃ュ織妗嗘灦鐨凩oggerFactory宸ュ巶绫伙紝鐢ㄤ簬鑾峰彇Logger瀹炰緥瀵硅薄
import org.slf4j.LoggerFactory;

/**
 * 鏁版嵁搴撹繛鎺ュ伐鍏风被
 * 璐熻矗鏁版嵁搴撹繛鎺ョ殑鑾峰彇銆佸叧闂€佽繛鎺ユ祴璇曠瓑缁熶竴绠＄悊锛屾彁渚涢潤鎬佸伐鍏锋柟娉曚緵鍏ㄥ眬璋冪敤
 */
public class DatabaseUtil {
    // 澹版槑骞跺垵濮嬪寲SLF4J鐨勬棩蹇楄褰曞櫒锛岀粦瀹氬綋鍓岲atabaseUtil绫伙紝鐢ㄤ簬鎵撳嵃褰撳墠绫荤殑鏃ュ織淇℃伅
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtil.class);

    // 鏁版嵁搴撹繛鎺ュ弬鏁?- 鏁版嵁搴撹繛鎺RL锛屼娇鐢╢inal+static淇グ涓哄叏灞€甯搁噺锛屼笉鍙慨鏀?
    // jdbc:mysql:// 鍥哄畾鍗忚澶达紝琛ㄧずJDBC杩炴帴MySQL
    // localhost:3306 鏁版嵁搴撴湇鍔″櫒鍦板潃+绔彛鍙?
    // dormitory_management 瑕佽繛鎺ョ殑鏁版嵁搴撳簱鍚?
    // 鍚庣紑鍙傛暟锛歶seSSL=false 鍏抽棴SSL鍔犲瘑锛堟湰鍦板紑鍙戞棤闇€锛夛紝serverTimezone=Asia/Shanghai 鎸囧畾鏃跺尯涓轰笂娴凤紝瑙ｅ喅鏃跺尯鎶ラ敊
    // allowPublicKeyRetrieval=true 鍏佽鑾峰彇鏁版嵁搴撳叕閽ワ紝瑙ｅ喅8.x鐗堟湰MySQL杩炴帴鏉冮檺闂
    private static final String URL = "jdbc:mysql://localhost:3306/dormitory_management?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    // 鏁版嵁搴撶櫥褰曠敤鎴峰悕锛宺oot涓篗ySQL榛樿瓒呯骇绠＄悊鍛樿处鍙?
    private static final String USER = "root";
    // 鏁版嵁搴撶櫥褰曞瘑鐮?
    private static final String PASSWORD = "123456";

    // 鏁版嵁搴撻┍鍔ㄧ被鍏ㄩ檺瀹氱被鍚嶏紝MySQL8.x鐗堟湰鐨勯┍鍔ㄧ被鏄痗om.mysql.cj.jdbc.Driver锛?.x鐗堟湰鏄痗om.mysql.jdbc.Driver
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // 闈欐€佸垵濮嬪寲鍧楋紝鐗圭偣锛氬睘浜庣被鏈韩锛屽湪褰撳墠绫荤涓€娆¤鍔犺浇鍒板唴瀛樻椂鎵ц锛屼笖鍙墽琛屼竴娆?
    // 浣滅敤锛氶」鐩惎鍔ㄦ椂鎻愬墠鍔犺浇鏁版嵁搴撻┍鍔紝閬垮厤姣忔鑾峰彇杩炴帴閮藉姞杞斤紝鎻愬崌鎬ц兘
    static {
        try {
            // Class.forName() 鍙嶅皠鍔犺浇鎸囧畾鐨勯┍鍔ㄧ被鍒癑VM鍐呭瓨涓?
            // 鍔犺浇鍚庨┍鍔ㄧ被浼氳嚜鍔ㄦ敞鍐屽埌DriverManager涓紝渚涘悗缁幏鍙栬繛鎺ヤ娇鐢?
            Class.forName(DRIVER);
            // 鏃ュ織鎵撳嵃锛氶┍鍔ㄥ姞杞芥垚鍔熺殑INFO绾у埆鏃ュ織锛屼究浜庢帓鏌ュ惎鍔ㄩ棶棰?
            logger.info("鏁版嵁搴撻┍鍔ㄥ姞杞芥垚鍔?);
        } catch (ClassNotFoundException e) {
            // 鎹曡幏椹卞姩绫诲姞杞藉け璐ョ殑寮傚父锛堝椹卞姩jar鍖呯己澶便€佺被璺緞鍐欓敊锛?
            // 鎵撳嵃ERROR绾у埆鏃ュ織锛屼紶鍏ュ紓甯稿璞★紝鍙畬鏁存墦鍗板爢鏍堜俊鎭?
            logger.error("鏁版嵁搴撻┍鍔ㄥ姞杞藉け璐?, e);
            // 鎶涘嚭杩愯鏃跺紓甯革紝缁堟绋嬪簭鍚姩锛屽洜涓洪┍鍔ㄥ姞杞藉け璐ュ悗缁棤娉曡繘琛屼换浣曟暟鎹簱鎿嶄綔
            throw new RuntimeException("鏁版嵁搴撻┍鍔ㄥ姞杞藉け璐?, e);
        }
    }

    /**
     * 鑾峰彇鏁版嵁搴撹繛鎺ョ殑鏍稿績宸ュ叿鏂规硶
     * @return Connection 鏁版嵁搴撹繛鎺ュ璞★紝閫氳繃璇ュ璞″彲浠ユ墽琛孲QL璇彞銆佹搷浣滄暟鎹簱
     * @throws SQLException 鎶涘嚭鏁版嵁搴撹繛鎺ュ紓甯革紝浜ょ粰璋冪敤鑰呭鐞嗭紝涓嶅湪杩欓噷鎹曡幏鍚炴帀寮傚父
     */
    public static Connection getConnection() throws SQLException {
        try {
            // 璋冪敤DriverManager鐨勯潤鎬佹柟娉曪紝浼犲叆URL銆佺敤鎴峰悕銆佸瘑鐮侊紝鑾峰彇鏁版嵁搴撹繛鎺ュ璞?
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 鎵撳嵃DEBUG绾у埆鏃ュ織锛屾爣璁拌繛鎺ユ垚鍔燂紝DEBUG绾у埆鐢ㄤ簬璇︾粏璋冭瘯淇℃伅
            logger.debug("鏁版嵁搴撹繛鎺ユ垚鍔?);
            // 杩斿洖鑾峰彇鍒扮殑鏁版嵁搴撹繛鎺ュ璞?
            return conn;
        } catch (SQLException e) {
            // 鎹曡幏鏁版嵁搴撹繛鎺ュけ璐ョ殑寮傚父锛堝鍦板潃閿欒銆佽处鍙峰瘑鐮侀敊璇€佹暟鎹簱鏈惎鍔ㄧ瓑锛?
            logger.error("鏁版嵁搴撹繛鎺ュけ璐?, e);
            // 鎶婂紓甯稿師灏佷笉鍔ㄦ姏鍑猴紝璁╄皟鐢ㄨ€呮劅鐭ヨ繛鎺ュけ璐ュ苟澶勭悊
            throw e;
        }
    }

    /**
     * 鍏抽棴鏁版嵁搴撹繛鎺ョ殑宸ュ叿鏂规硶锛岄噴鏀炬暟鎹簱杩炴帴璧勬簮
     * 鏁版嵁搴撹繛鎺ユ槸绋€缂鸿祫婧愶紝浣跨敤瀹屾瘯蹇呴』鍏抽棴锛屽惁鍒欎細閫犳垚杩炴帴娉勬紡锛屾渶缁堟暟鎹簱杩炴帴姹犺€楀敖
     * @param conn 瑕佸叧闂殑鏁版嵁搴撹繛鎺ュ璞★紝鍙互涓簄ull
     */
    public static void closeConnection(Connection conn) {
        // 闈炵┖鍒ゆ柇锛氶伩鍏嶄紶鍏ull鏃惰皟鐢╟lose()鏂规硶鎶涘嚭绌烘寚閽堝紓甯?
        if (conn != null) {
            try {
                // 璋冪敤杩炴帴瀵硅薄鐨刢lose()鏂规硶锛岄噴鏀剧墿鐞嗚繛鎺ワ紝褰掕繕鍒版暟鎹簱杩炴帴姹?
                conn.close();
                // 鎵撳嵃DEBUG绾у埆鏃ュ織锛屾爣璁拌繛鎺ュ凡鍏抽棴
                logger.debug("鏁版嵁搴撹繛鎺ュ凡鍏抽棴");
            } catch (SQLException e) {
                // 鎹曡幏鍏抽棴杩炴帴鏃剁殑寮傚父锛堝杩炴帴宸插け鏁堛€佺綉缁滀腑鏂瓑锛?
                logger.error("鍏抽棴鏁版嵁搴撹繛鎺ュけ璐?, e);
            }
        }
    }

    /**
     * 娴嬭瘯鏁版嵁搴撹繛鎺ユ槸鍚﹀彲鐢ㄧ殑宸ュ叿鏂规硶
     * 鐢ㄤ簬椤圭洰鑷銆佸仴搴锋鏌ワ紝蹇€熷垽鏂暟鎹簱閰嶇疆鏄惁姝ｇ‘銆佹暟鎹簱鏈嶅姟鏄惁姝ｅ父
     * @return boolean 杩炴帴娴嬭瘯缁撴灉锛歵rue=杩炴帴鎴愬姛锛宖alse=杩炴帴澶辫触
     */
    public static boolean testConnection() {
        // 澹版槑杩炴帴瀵硅薄锛屽垵濮嬪€间负null锛岀敤浜庢帴鏀惰幏鍙栧埌鐨勮繛鎺?
        Connection conn = null;
        try {
            // 璋冪敤鏈被鐨刧etConnection()鏂规硶锛岃幏鍙栨暟鎹簱杩炴帴
            conn = getConnection();
            // 鍒ゆ柇杩炴帴鏄惁鏈夋晥锛氳繛鎺ュ璞′笉涓虹┖ 骞朵笖 杩炴帴鏈鍏抽棴
            boolean isConnected = conn != null && !conn.isClosed();
            // 鏍规嵁杩炴帴鐘舵€佹墦鍗板搴旀棩蹇?
            if (isConnected) {
                logger.info("鏁版嵁搴撹繛鎺ユ祴璇曟垚鍔?);
            } else {
                logger.error("鏁版嵁搴撹繛鎺ユ祴璇曞け璐ワ細杩炴帴涓虹┖鎴栧凡鍏抽棴");
            }
            // 杩斿洖杩炴帴娴嬭瘯缁撴灉
            return isConnected;
        } catch (SQLException e) {
            // 鎹曡幏杩炴帴娴嬭瘯杩囩▼涓殑鎵€鏈夋暟鎹簱寮傚父
            logger.error("鏁版嵁搴撹繛鎺ユ祴璇曞け璐?, e);
            // 杩炴帴澶辫触杩斿洖false
            return false;
        } finally {
            // finally浠ｇ爜鍧楋細鏃犺try/catch鏄惁鎵ц銆佹槸鍚︽姏鍑哄紓甯革紝鏈€缁堥兘浼氭墽琛?
            // 鏍稿績浣滅敤锛氱‘淇濊繛鎺ヨ祫婧愪竴瀹氫細琚噴鏀撅紝闃叉鍐呭瓨娉勬紡
            closeConnection(conn);
        }
    }
}

========================================
DataUpdateManager.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\util
========================================
package org.dorm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 鏁版嵁鏇存柊绠＄悊鍣?- 鍗曚緥妯″紡
 * 鐢ㄤ簬鍦ㄥ悇涓狢ontroller涔嬮棿浼犻€掓暟鎹洿鏂伴€氱煡
 */
public class DataUpdateManager {

    // 鍗曚緥瀹炰緥
    private static DataUpdateManager instance;

    // 鏁版嵁鏇存柊鐩戝惉鍣ㄦ帴鍙?
    public interface DataUpdateListener {
        void onDataUpdated(String dataType, String operation);
    }

    // 鐩戝惉鍣ㄥ垪琛?
    private List<DataUpdateListener> listeners = new ArrayList<>();

    // 绉佹湁鏋勯€犲嚱鏁?
    private DataUpdateManager() {}

    // 鑾峰彇鍗曚緥瀹炰緥
    public static synchronized DataUpdateManager getInstance() {
        if (instance == null) {
            instance = new DataUpdateManager();
        }
        return instance;
    }

    // 娉ㄥ唽鐩戝惉鍣?
    public void addListener(DataUpdateListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    // 绉婚櫎鐩戝惉鍣?
    public void removeListener(DataUpdateListener listener) {
        listeners.remove(listener);
    }

    // 閫氱煡鎵€鏈夌洃鍚櫒鏁版嵁宸叉洿鏂?
    public void notifyDataUpdated(String dataType, String operation) {
        for (DataUpdateListener listener : listeners) {
            try {
                listener.onDataUpdated(dataType, operation);
            } catch (Exception e) {
                // 闃叉涓€涓洃鍚櫒鐨勫紓甯稿奖鍝嶅叾浠栫洃鍚櫒
                System.err.println("鏁版嵁鏇存柊鐩戝惉鍣ㄥ紓甯? " + e.getMessage());
            }
        }
    }

    // 渚挎嵎鏂规硶 - 瀛︾敓鏁版嵁鏇存柊
    public void notifyStudentDataChanged(String operation) {
        notifyDataUpdated("student", operation);
    }

    // 渚挎嵎鏂规硶 - 瀹胯垗鏁版嵁鏇存柊
    public void notifyDormitoryDataChanged(String operation) {
        notifyDataUpdated("dormitory", operation);
    }

    // 渚挎嵎鏂规硶 - 缂磋垂鏁版嵁鏇存柊
    public void notifyPaymentDataChanged(String operation) {
        notifyDataUpdated("payment", operation);
    }

    // 渚挎嵎鏂规硶 - 杩濈邯鏁版嵁鏇存柊
    public void notifyViolationDataChanged(String operation) {
        notifyDataUpdated("violation", operation);
    }

    // 渚挎嵎鏂规硶 - 缁翠慨鐢宠鏁版嵁鏇存柊
    public void notifyRepairDataChanged(String operation) {
        notifyDataUpdated("repair", operation);
    }
}

========================================
AccommodationManagementController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.dorm.controller.AccommodationController;
import org.dorm.model.entity.Accommodation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 浣忓鍒嗛厤绠＄悊鐣岄潰鎺у埗鍣?
 */
public class AccommodationManagementController {
    private static final Logger logger = LoggerFactory.getLogger(AccommodationManagementController.class);

    @FXML
    private Button assignBedButton;
    @FXML
    private Button changeBedButton;
    @FXML
    private Button checkoutButton;
    @FXML
    private Button refreshButton;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> searchTypeCombo;
    @FXML
    private Label statusLabel;
    @FXML
    private Label availableBedsLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label lastUpdateLabel;
    @FXML
    private TableView<Accommodation> accommodationTable;
    @FXML
    private TableColumn<Accommodation, String> studentIdColumn;
    @FXML
    private TableColumn<Accommodation, String> studentNameColumn;
    @FXML
    private TableColumn<Accommodation, String> collegeColumn;
    @FXML
    private TableColumn<Accommodation, String> classColumn;
    @FXML
    private TableColumn<Accommodation, String> dormitoryColumn;
    @FXML
    private TableColumn<Accommodation, String> bedIdColumn;
    @FXML
    private TableColumn<Accommodation, String> checkinDateColumn;
    @FXML
    private TableColumn<Accommodation, String> statusColumn;

    private ObservableList<Accommodation> accommodationData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        logger.info("浣忓鍒嗛厤绠＄悊鐣岄潰鍒濆鍖栧紑濮?);

        // 鍒濆鍖栬〃鏍煎垪
        setupTableColumns();

        // 鍒濆鍖栨悳绱㈢被鍨?
        if (searchTypeCombo != null) {
            searchTypeCombo.setItems(FXCollections.observableArrayList("鍏ㄩ儴", "瀛﹀彿", "濮撳悕", "瀹胯垗"));
            searchTypeCombo.setValue("鍏ㄩ儴");
        }

        // 鍔犺浇鍒濆鏁版嵁
        loadAccommodations();

        logger.info("浣忓鍒嗛厤绠＄悊鐣岄潰鍒濆鍖栧畬鎴?);
    }

    /**
     * 鍒濆鍖栬〃鏍煎垪
     */
    private void setupTableColumns() {
        if (studentIdColumn != null) {
            studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        }
        if (studentNameColumn != null) {
            studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        }
        if (collegeColumn != null) {
            collegeColumn.setCellValueFactory(new PropertyValueFactory<>("collegeName"));
        }
        if (classColumn != null) {
            classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        }
        if (dormitoryColumn != null) {
            dormitoryColumn.setCellValueFactory(new PropertyValueFactory<>("dormitoryId"));
        }
        if (bedIdColumn != null) {
            bedIdColumn.setCellValueFactory(new PropertyValueFactory<>("bedId"));
        }
        if (checkinDateColumn != null) {
            checkinDateColumn.setCellValueFactory(cellData -> {
                Accommodation accommodation = cellData.getValue();
                java.util.Date checkinDate = accommodation.getCheckinDate();
                if (checkinDate != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return new javafx.beans.property.SimpleStringProperty(sdf.format(checkinDate));
                } else {
                    return new javafx.beans.property.SimpleStringProperty("");
                }
            });
        }
        if (statusColumn != null) {
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        }

        // 璁剧疆琛ㄦ牸鏁版嵁
        if (accommodationTable != null) {
            accommodationTable.setItems(accommodationData);
        }
    }

    /**
     * 鍔犺浇浣忓鏁版嵁
     */
    private void loadAccommodations() {
        try {
            logger.info("寮€濮嬪姞杞戒綇瀹挎暟鎹?..");

            // 鍒涘缓鎺у埗鍣ㄥ疄渚?
            AccommodationController accommodationController = new AccommodationController();

            // 鑾峰彇浣忓鏁版嵁
            List<Accommodation> accommodations = accommodationController.getAllAccommodations();

            accommodationData.clear();
            accommodationData.addAll(accommodations);

            String statusText = "鍏?" + accommodations.size() + " 鏉¤褰?;
            if (statusLabel != null) {
                statusLabel.setText(statusText);
            }

            showMessage("鏁版嵁鍔犺浇瀹屾垚", "success");

            logger.info("浣忓鏁版嵁鍔犺浇鎴愬姛锛歿}鏉¤褰?, accommodations.size());

        } catch (Exception e) {
            logger.error("鍔犺浇浣忓鏁版嵁澶辫触", e);
            if (statusLabel != null) {
                statusLabel.setText("鍔犺浇澶辫触");
            }
            showMessage("鍔犺浇鏁版嵁澶辫触锛? + e.getMessage(), "error");
        }
    }

    @FXML
    private void showAssignBedDialog(ActionEvent event) {
        // 鍒涘缓鍒嗛厤搴婁綅瀵硅瘽妗?
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("鍒嗛厤搴婁綅");
        dialog.setHeaderText("涓哄鐢熷垎閰嶅簥浣?);

        // 璁剧疆鎸夐挳
        ButtonType assignButtonType = new ButtonType("鍒嗛厤", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(assignButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentIdField = new TextField();
        studentIdField.setPromptText("杈撳叆瀛︾敓瀛﹀彿");
        ComboBox<String> bedCombo = new ComboBox<>();
        Label infoLabel = new Label("璇峰厛杈撳叆瀛︾敓瀛﹀彿");

        // 瀛︾敓瀛﹀彿杈撳叆鐩戝惉鍣?
        studentIdField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.trim().isEmpty()) {
                try {
                    AccommodationController controller = new AccommodationController();
                    List<String> availableBeds = controller.getAvailableBeds();
                    bedCombo.getItems().clear();
                    bedCombo.getItems().addAll(availableBeds);
                    infoLabel.setText("鎵惧埌 " + availableBeds.size() + " 涓┖闂插簥浣?);
                } catch (Exception e) {
                    infoLabel.setText("鍔犺浇搴婁綅澶辫触锛? + e.getMessage());
                    logger.error("鍔犺浇搴婁綅澶辫触", e);
                }
            }
        });

        grid.add(new Label("瀛︾敓瀛﹀彿:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("閫夋嫨搴婁綅:"), 0, 1);
        grid.add(bedCombo, 1, 1);
        grid.add(infoLabel, 0, 2, 2, 1);

        dialog.getDialogPane().setContent(grid);

        // 楠岃瘉杈撳叆
        dialog.getDialogPane().lookupButton(assignButtonType).setDisable(true);
        bedCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            dialog.getDialogPane().lookupButton(assignButtonType).setDisable(
                newVal == null || studentIdField.getText().trim().isEmpty());
        });

        // 澶勭悊缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == assignButtonType) {
                String studentId = studentIdField.getText().trim();
                String bedId = bedCombo.getValue();
                if (studentId != null && bedId != null) {
                    try {
                        AccommodationController controller = new AccommodationController();
                        boolean success = controller.assignBed(studentId, bedId);
                        if (success) {
                            showMessage("鍒嗛厤搴婁綅鎴愬姛锛?, "success");
                            loadAccommodations();
                            logger.info("鍒嗛厤搴婁綅鎴愬姛锛氬鐢焮} -> 搴婁綅{}", studentId, bedId);
                        } else {
                            showMessage("鍒嗛厤搴婁綅澶辫触", "error");
                        }
                    } catch (Exception e) {
                        logger.error("鍒嗛厤搴婁綅寮傚父", e);
                        showMessage("鍒嗛厤搴婁綅澶辫触锛? + e.getMessage(), "error");
                    }
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    @FXML
    private void showChangeBedDialog(ActionEvent event) {
        Accommodation selectedAccommodation = accommodationTable.getSelectionModel().getSelectedItem();
        if (selectedAccommodation == null) {
            showMessage("璇峰厛閫夋嫨瑕佽皟鎹㈠簥浣嶇殑瀛︾敓锛?, "warning");
            return;
        }

        if (!"宸插叆浣?.equals(selectedAccommodation.getStatus())) {
            showMessage("鍙湁宸插叆浣忕殑瀛︾敓鎵嶈兘璋冩崲搴婁綅锛?, "warning");
            return;
        }

        // 鍒涘缓璋冩崲搴婁綅瀵硅瘽妗?
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("璋冩崲搴婁綅");
        dialog.setHeaderText("涓哄鐢?" + selectedAccommodation.getStudentName() + " 璋冩崲搴婁綅");

        // 璁剧疆鎸夐挳
        ButtonType changeButtonType = new ButtonType("璋冩崲", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<String> bedCombo = new ComboBox<>();
        Label currentBedLabel = new Label("褰撳墠搴婁綅: " + selectedAccommodation.getBedId());

        // 鍔犺浇绌洪棽搴婁綅
        try {
            AccommodationController controller = new AccommodationController();
            List<String> availableBeds = controller.getAvailableBeds();
            bedCombo.getItems().addAll(availableBeds);
        } catch (Exception e) {
            logger.error("鍔犺浇搴婁綅鍒楄〃澶辫触", e);
            showMessage("鍔犺浇搴婁綅鍒楄〃澶辫触锛? + e.getMessage(), "error");
            return;
        }

        grid.add(currentBedLabel, 0, 0, 2, 1);
        grid.add(new Label("閫夋嫨鏂板簥浣?"), 0, 1);
        grid.add(bedCombo, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // 楠岃瘉杈撳叆
        dialog.getDialogPane().lookupButton(changeButtonType).setDisable(true);
        bedCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            dialog.getDialogPane().lookupButton(changeButtonType).setDisable(newVal == null);
        });

        // 澶勭悊缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == changeButtonType) {
                String newBed = bedCombo.getValue();
                if (newBed != null) {
                    try {
                        AccommodationController controller = new AccommodationController();
                        boolean success = controller.changeBed(selectedAccommodation.getStudentId(), newBed);
                        if (success) {
                            showMessage("璋冩崲搴婁綅鎴愬姛锛?, "success");
                            loadAccommodations();
                            logger.info("璋冩崲搴婁綅鎴愬姛锛氬鐢焮} -> 鏂板簥浣峽}",
                                selectedAccommodation.getStudentId(), newBed);
                        } else {
                            showMessage("璋冩崲搴婁綅澶辫触", "error");
                        }
                    } catch (Exception e) {
                        logger.error("璋冩崲搴婁綅寮傚父", e);
                        showMessage("璋冩崲搴婁綅澶辫触锛? + e.getMessage(), "error");
                    }
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    @FXML
    private void showCheckoutDialog(ActionEvent event) {
        Accommodation selectedAccommodation = accommodationTable.getSelectionModel().getSelectedItem();
        if (selectedAccommodation == null) {
            showMessage("璇峰厛閫夋嫨瑕侀€€瀹跨殑瀛︾敓锛?, "warning");
            return;
        }

        if (!"宸插叆浣?.equals(selectedAccommodation.getStatus())) {
            showMessage("鍙湁宸插叆浣忕殑瀛︾敓鎵嶈兘閫€瀹匡紒", "warning");
            return;
        }

        // 鍒涘缓纭瀵硅瘽妗?
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("纭閫€瀹?);
        alert.setHeaderText("纭瑕佽瀛︾敓 " + selectedAccommodation.getStudentName() + " 閫€瀹垮悧锛?);
        alert.setContentText("閫€瀹垮悗锛岃瀛︾敓鐨勫簥浣嶅皢琚噴鏀撅紝鍙互閲嶆柊鍒嗛厤缁欏叾浠栧鐢熴€?);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    AccommodationController controller = new AccommodationController();
                    boolean success = controller.checkout(selectedAccommodation.getStudentId());
                    if (success) {
                        showMessage("閫€瀹挎垚鍔燂紒", "success");
                        loadAccommodations();
                        logger.info("閫€瀹挎垚鍔燂細瀛︾敓{}", selectedAccommodation.getStudentId());
                    } else {
                        showMessage("閫€瀹垮け璐?, "error");
                    }
                } catch (Exception e) {
                    logger.error("閫€瀹垮紓甯?, e);
                    showMessage("閫€瀹垮け璐ワ細" + e.getMessage(), "error");
                }
            }
        });
    }

    @FXML
    private void refreshAccommodations(ActionEvent event) {
        loadAccommodations();
    }

    @FXML
    private void searchAccommodations(ActionEvent event) {
        String searchText = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            loadAccommodations(); // 濡傛灉鎼滅储鏂囨湰涓虹┖锛屽姞杞芥墍鏈夋暟鎹?
            return;
        }

        try {
            AccommodationController controller = new AccommodationController();
            List<Accommodation> results = controller.searchAccommodations(searchText, searchType);

            accommodationData.clear();
            accommodationData.addAll(results);

            String statusText = "鎵惧埌 " + results.size() + " 鏉¤褰?;
            if (statusLabel != null) {
                statusLabel.setText(statusText);
            }

            showMessage("鎼滅储瀹屾垚", "success");
            logger.info("鎼滅储浣忓淇℃伅锛氱被鍨?{}, 鍏抽敭璇?{}, 缁撴灉={}", searchType, searchText, results.size());

        } catch (Exception e) {
            logger.error("鎼滅储浣忓淇℃伅澶辫触", e);
            showMessage("鎼滅储澶辫触锛? + e.getMessage(), "error");
        }
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message, String type) {
        if (messageLabel != null) {
            messageLabel.setText(message);
            // 杩欓噷鍙互鏍规嵁type璁剧疆涓嶅悓鐨勬牱寮?
        }
        logger.info("鏄剧ず娑堟伅: {} ({})", message, type);
    }
}

========================================
AdminRepairController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dorm.controller.RepairApplicationController;
import org.dorm.model.entity.RepairApplication;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 绠＄悊鍛樼淮淇鐞嗙晫闈㈡帶鍒跺櫒
 */
public class AdminRepairController {
    private static final Logger logger = LoggerFactory.getLogger(AdminRepairController.class);

    @FXML
    private Button acceptButton;
    @FXML
    private Button completeButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<RepairApplication> repairTable;
    @FXML
    private TableColumn<RepairApplication, String> applyIdColumn;
    @FXML
    private TableColumn<RepairApplication, String> studentIdColumn;
    @FXML
    private TableColumn<RepairApplication, String> studentNameColumn;
    @FXML
    private TableColumn<RepairApplication, String> faultLocationColumn;
    @FXML
    private TableColumn<RepairApplication, String> faultDescColumn;
    @FXML
    private TableColumn<RepairApplication, String> applyTimeColumn;
    @FXML
    private TableColumn<RepairApplication, String> statusColumn;
    @FXML
    private TableColumn<RepairApplication, String> handlerColumn;
    @FXML
    private TableColumn<RepairApplication, String> finishTimeColumn;

    private RepairApplicationController repairController = new RepairApplicationController();
    private ObservableList<RepairApplication> repairData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // 鍒濆鍖栬〃鏍煎垪
        applyIdColumn.setCellValueFactory(new PropertyValueFactory<>("applyId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty("鏈煡"));
        faultLocationColumn.setCellValueFactory(new PropertyValueFactory<>("faultLocation"));
        faultDescColumn.setCellValueFactory(new PropertyValueFactory<>("faultDesc"));
        applyTimeColumn.setCellValueFactory(cellData -> {
            RepairApplication app = cellData.getValue();
            String timeStr = app.getApplyTime() != null ?
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(app.getApplyTime()) : "";
            return new javafx.beans.property.SimpleStringProperty(timeStr);
        });
        statusColumn.setCellValueFactory(cellData -> {
            String status = cellData.getValue().getStatus();
            // 灏嗚嫳鏂囩姸鎬佽浆鎹负涓枃鏄剧ず
            String displayStatus;
            switch (status) {
                case "pending":
                    displayStatus = "寰呭彈鐞?;
                    break;
                case "accepted":
                    displayStatus = "宸插彈鐞?;
                    break;
                case "repairing":
                    displayStatus = "缁翠慨涓?;
                    break;
                case "completed":
                    displayStatus = "宸插畬鎴?;
                    break;
                default:
                    displayStatus = status;
                    break;
            }
            return new javafx.beans.property.SimpleStringProperty(displayStatus);
        });

        // 娣诲姞鐘舵€佽浆鎹㈡柟娉?
        repairTable.setRowFactory(tv -> new TableRow<RepairApplication>() {
            @Override
            protected void updateItem(RepairApplication item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    // 鏍规嵁鐘舵€佽缃鏍峰紡
                    switch (item.getStatus()) {
                        case "pending":
                            setStyle("-fx-background-color: #fff3cd;"); // 娴呴粍鑹?
                            break;
                        case "accepted":
                            setStyle("-fx-background-color: #d1ecf1;"); // 娴呰摑鑹?
                            break;
                        case "repairing":
                            setStyle("-fx-background-color: #d4edda;"); // 娴呯豢鑹?
                            break;
                        case "completed":
                            setStyle("-fx-background-color: #f8f9fa;"); // 鐏拌壊
                            break;
                        default:
                            setStyle("");
                            break;
                    }
                }
            }
        });
        handlerColumn.setCellValueFactory(new PropertyValueFactory<>("handler"));
        finishTimeColumn.setCellValueFactory(cellData -> {
            RepairApplication app = cellData.getValue();
            String timeStr = app.getFinishTime() != null ?
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(app.getFinishTime()) : "";
            return new javafx.beans.property.SimpleStringProperty(timeStr);
        });

        // 璁剧疆琛ㄦ牸鏁版嵁
        repairTable.setItems(repairData);

        // 鍔犺浇鍒濆鏁版嵁
        loadRepairApplications();

        logger.info("绠＄悊鍛樼淮淇鐞嗙晫闈㈠垵濮嬪寲瀹屾垚");
    }

    /**
     * 鍔犺浇缁翠慨鐢宠鏁版嵁
     */
    private void loadRepairApplications() {
        try {
            List<RepairApplication> applications = repairController.getAllApplications();
            repairData.clear();
            repairData.addAll(applications);

            statusLabel.setText("鍏? + applications.size() + "鏉¤褰?);
            showMessage("鏁版嵁鍔犺浇瀹屾垚");

            logger.info("鍔犺浇缁翠慨鐢宠鏁版嵁鎴愬姛锛歿}鏉¤褰?, applications.size());

        } catch (Exception e) {
            logger.error("鍔犺浇缁翠慨鐢宠鏁版嵁澶辫触", e);
            showMessage("鍔犺浇鏁版嵁澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 鍙楃悊缁翠慨鐢宠
     */
    @FXML
    private void acceptApplication(ActionEvent event) {
        RepairApplication selectedApplication = repairTable.getSelectionModel().getSelectedItem();
        if (selectedApplication == null) {
            showMessage("璇峰厛閫夋嫨瑕佸彈鐞嗙殑鐢宠锛?);
            return;
        }

        if (!"pending".equals(selectedApplication.getStatus()) &&
            !"寰呭彈鐞?.equals(selectedApplication.getStatus())) {
            showMessage("鍙兘鍙楃悊鐘舵€佷负'寰呭彈鐞?鐨勭敵璇凤紒");
            return;
        }

        try {
            boolean result = repairController.updateApplicationStatus(
                selectedApplication.getApplyId(), "accepted", "绠＄悊鍛?);

            if (result) {
                showMessage("鐢宠鍙楃悊鎴愬姛锛?);
                loadRepairApplications(); // 鍒锋柊鏁版嵁
                // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                DataUpdateManager.getInstance().notifyRepairDataChanged("accept");
                logger.info("缁翠慨鐢宠鍙楃悊鎴愬姛锛歿}", selectedApplication.getApplyId());
            } else {
                showMessage("鐢宠鍙楃悊澶辫触锛?);
            }

        } catch (Exception e) {
            logger.error("鍙楃悊缁翠慨鐢宠澶辫触", e);
            showMessage("鍙楃悊澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 瀹屾垚缁翠慨鐢宠
     */
    @FXML
    private void completeApplication(ActionEvent event) {
        RepairApplication selectedApplication = repairTable.getSelectionModel().getSelectedItem();
        if (selectedApplication == null) {
            showMessage("璇峰厛閫夋嫨瑕佸畬鎴愮殑鐢宠锛?);
            return;
        }

        if (!"accepted".equals(selectedApplication.getStatus()) &&
            !"repairing".equals(selectedApplication.getStatus()) &&
            !"宸插彈鐞?.equals(selectedApplication.getStatus()) &&
            !"缁翠慨涓?.equals(selectedApplication.getStatus())) {
            showMessage("鍙兘瀹屾垚鐘舵€佷负'宸插彈鐞?鎴?缁翠慨涓?鐨勭敵璇凤紒");
            return;
        }

        try {
            boolean result = repairController.completeRepairApplication(selectedApplication.getApplyId());

            if (result) {
                showMessage("缁翠慨瀹屾垚鎴愬姛锛?);
                loadRepairApplications(); // 鍒锋柊鏁版嵁
                // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                DataUpdateManager.getInstance().notifyRepairDataChanged("complete");
                logger.info("缁翠慨鐢宠瀹屾垚鎴愬姛锛歿}", selectedApplication.getApplyId());
            } else {
                showMessage("缁翠慨瀹屾垚澶辫触锛?);
            }

        } catch (Exception e) {
            logger.error("瀹屾垚缁翠慨鐢宠澶辫触", e);
            showMessage("瀹屾垚澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 鍒锋柊缁翠慨鐢宠鍒楄〃
     */
    @FXML
    private void refreshApplications(ActionEvent event) {
        loadRepairApplications();
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
        // 3绉掑悗娓呴櫎娑堟伅
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> messageLabel.setText(""));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

========================================
ChangePasswordController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dorm.controller.LoginController;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 淇敼瀵嗙爜瀵硅瘽妗嗘帶鍒跺櫒
 */
public class ChangePasswordController {
    private static final Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);

    @FXML
    private Label userInfoLabel;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    private LoginController loginController = new LoginController();
    private User currentUser;

    @FXML
    private void initialize() {
        logger.info("淇敼瀵嗙爜瀵硅瘽妗嗗垵濮嬪寲瀹屾垚");
    }

    /**
     * 璁剧疆褰撳墠鐢ㄦ埛
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        if (user != null) {
            userInfoLabel.setText("褰撳墠鐢ㄦ埛锛? + user.getUserId());
        }
    }

    /**
     * 澶勭悊纭鎸夐挳鐐瑰嚮浜嬩欢
     */
    @FXML
    private void handleConfirm(ActionEvent event) {
        if (currentUser == null) {
            showMessage("鐢ㄦ埛淇℃伅鏈缃?);
            return;
        }

        // 鑾峰彇杈撳叆鏁版嵁
        String oldPassword = oldPasswordField.getText().trim();
        String newPassword = newPasswordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        // 楠岃瘉杈撳叆
        if (oldPassword.isEmpty()) {
            showMessage("璇疯緭鍏ュ綋鍓嶅瘑鐮侊紒");
            oldPasswordField.requestFocus();
            return;
        }

        if (newPassword.isEmpty()) {
            showMessage("璇疯緭鍏ユ柊瀵嗙爜锛?);
            newPasswordField.requestFocus();
            return;
        }

        if (newPassword.length() < 6 || newPassword.length() > 20) {
            showMessage("鏂板瘑鐮侀暱搴﹀繀椤诲湪6-20浣嶄箣闂达紒");
            newPasswordField.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            showMessage("璇风‘璁ゆ柊瀵嗙爜锛?);
            confirmPasswordField.requestFocus();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showMessage("涓ゆ杈撳叆鐨勬柊瀵嗙爜涓嶄竴鑷达紒");
            confirmPasswordField.clear();
            confirmPasswordField.requestFocus();
            return;
        }

        if (oldPassword.equals(newPassword)) {
            showMessage("鏂板瘑鐮佷笉鑳戒笌褰撳墠瀵嗙爜鐩稿悓锛?);
            newPasswordField.clear();
            confirmPasswordField.clear();
            newPasswordField.requestFocus();
            return;
        }

        // 绂佺敤纭鎸夐挳
        confirmButton.setDisable(true);
        confirmButton.setText("淇敼涓?..");

        try {
            // 淇敼瀵嗙爜
            boolean result = loginController.changePassword(currentUser.getUserId(), oldPassword, newPassword);

            if (result) {
                showMessage("瀵嗙爜淇敼鎴愬姛锛?);
                logger.info("瀵嗙爜淇敼鎴愬姛锛歿}", currentUser.getUserId());

                // 寤惰繜鍏抽棴瀵硅瘽妗?
                new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        javafx.application.Platform.runLater(this::closeDialog);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            } else {
                showMessage("瀵嗙爜淇敼澶辫触锛岃妫€鏌ュ綋鍓嶅瘑鐮佹槸鍚︽纭紒");
                confirmButton.setDisable(false);
                confirmButton.setText("纭");
            }

        } catch (Exception e) {
            logger.error("淇敼瀵嗙爜杩囩▼涓彂鐢熷紓甯?, e);
            showMessage("淇敼澶辫触锛? + e.getMessage());
            confirmButton.setDisable(false);
            confirmButton.setText("纭");
        }
    }

    /**
     * 澶勭悊鍙栨秷鎸夐挳鐐瑰嚮浜嬩欢
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        logger.info("鐢ㄦ埛鍙栨秷淇敼瀵嗙爜");
        closeDialog();
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * 鍏抽棴瀵硅瘽妗?
     */
    private void closeDialog() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}

========================================
DormitoryManagementController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.dorm.controller.DormitoryController;
import org.dorm.model.entity.Dormitory;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * 瀹胯垗绠＄悊鐣岄潰鎺у埗鍣?
 */
public class DormitoryManagementController {
    private static final Logger logger = LoggerFactory.getLogger(DormitoryManagementController.class);

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label lastUpdateLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<Dormitory> dormitoryTable;
    @FXML
    private TableColumn<Dormitory, String> dormIdColumn;
    @FXML
    private TableColumn<Dormitory, String> buildingColumn;
    @FXML
    private TableColumn<Dormitory, Integer> floorColumn;
    @FXML
    private TableColumn<Dormitory, Integer> capacityColumn;
    @FXML
    private TableColumn<Dormitory, String> managerColumn;
    @FXML
    private TableColumn<Dormitory, String> managerPhoneColumn;

    private ObservableList<Dormitory> dormitoryData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // 鍒濆鍖栬〃鏍煎垪
        dormIdColumn.setCellValueFactory(new PropertyValueFactory<>("dormitoryId"));
        buildingColumn.setCellValueFactory(new PropertyValueFactory<>("building"));
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("totalBeds"));
        managerColumn.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        managerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("managerPhone"));

        // 璁剧疆琛ㄦ牸鏁版嵁
        dormitoryTable.setItems(dormitoryData);

        // 鍔犺浇鍒濆鏁版嵁
        loadDormitories();

        logger.info("瀹胯垗绠＄悊鐣岄潰鍒濆鍖栧畬鎴?);
    }

    /**
     * 鍔犺浇瀹胯垗鏁版嵁
     */
    private void loadDormitories() {
        try {
            // 鎸夐渶鍒涘缓Controller瀹炰緥锛岄伩鍏嶅湪搴旂敤鍚姩鏃跺氨杩炴帴鏁版嵁搴?
            DormitoryController dormitoryController = new DormitoryController();
            // 浠庢暟鎹簱鑾峰彇瀹胯垗鏁版嵁
            List<Dormitory> dormitories = dormitoryController.getAllDormitories();
            dormitoryData.clear();
            dormitoryData.addAll(dormitories);

            statusLabel.setText("鍏?" + dormitories.size() + " 鏉¤褰?);
            updateLastUpdateTime();
            showMessage("鏁版嵁鍔犺浇瀹屾垚", "success");

            logger.info("鍔犺浇瀹胯垗鏁版嵁鎴愬姛锛歿}鏉¤褰?, dormitories.size());

        } catch (Exception e) {
            logger.error("鍔犺浇瀹胯垗鏁版嵁澶辫触", e);
            showMessage("鍔犺浇鏁版嵁澶辫触锛? + e.getMessage(), "error");
        }
    }


    /**
     * 鏄剧ず娣诲姞瀹胯垗瀵硅瘽妗?
     */
    @FXML
    private void showAddDialog(ActionEvent event) {
        // 鍒涘缓瀵硅瘽妗?
        Dialog<Dormitory> dialog = new Dialog<>();
        dialog.setTitle("娣诲姞瀹胯垗");
        dialog.setHeaderText("璇疯緭鍏ユ柊瀹胯垗鐨勪俊鎭?);

        // 璁剧疆鎸夐挳
        ButtonType saveButtonType = new ButtonType("淇濆瓨", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField dormIdField = new TextField();
        dormIdField.setPromptText("瀹胯垗鍙凤紙濡傦細A101锛?);
        TextField buildingField = new TextField();
        buildingField.setPromptText("妤兼爧锛堝锛欰鏍嬶級");
        Spinner<Integer> floorSpinner = new Spinner<>(1, 20, 1);
        floorSpinner.setEditable(true);
        Spinner<Integer> capacitySpinner = new Spinner<>(1, 10, 4);
        capacitySpinner.setEditable(true);
        TextField managerField = new TextField();
        managerField.setPromptText("绠＄悊鍛樺鍚?);
        TextField managerPhoneField = new TextField();
        managerPhoneField.setPromptText("绠＄悊鍛樼數璇?);

        grid.add(new Label("瀹胯垗鍙?"), 0, 0);
        grid.add(dormIdField, 1, 0);
        grid.add(new Label("妤兼爧:"), 0, 1);
        grid.add(buildingField, 1, 1);
        grid.add(new Label("妤煎眰:"), 0, 2);
        grid.add(floorSpinner, 1, 2);
        grid.add(new Label("瀹归噺:"), 0, 3);
        grid.add(capacitySpinner, 1, 3);
        grid.add(new Label("绠＄悊鍛?"), 0, 4);
        grid.add(managerField, 1, 4);
        grid.add(new Label("鑱旂郴鐢佃瘽:"), 0, 5);
        grid.add(managerPhoneField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // 楠岃瘉杈撳叆
        dialog.getDialogPane().lookupButton(saveButtonType).setDisable(true);
        dormIdField.textProperty().addListener((obs, oldText, newText) -> {
            boolean disable = dormIdField.getText().trim().isEmpty() ||
                             buildingField.getText().trim().isEmpty();
            dialog.getDialogPane().lookupButton(saveButtonType).setDisable(disable);
        });
        buildingField.textProperty().addListener((obs, oldText, newText) -> {
            boolean disable = dormIdField.getText().trim().isEmpty() ||
                             buildingField.getText().trim().isEmpty();
            dialog.getDialogPane().lookupButton(saveButtonType).setDisable(disable);
        });

        // 澶勭悊缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryId(dormIdField.getText().trim());
                dormitory.setBuilding(buildingField.getText().trim());
                dormitory.setFloor(floorSpinner.getValue());
                dormitory.setTotalBeds(capacitySpinner.getValue());
                dormitory.setManagerName(managerField.getText().trim().isEmpty() ? null : managerField.getText().trim());
                dormitory.setManagerPhone(managerPhoneField.getText().trim().isEmpty() ? null : managerPhoneField.getText().trim());
                return dormitory;
            }
            return null;
        });

        Optional<Dormitory> result = dialog.showAndWait();
        result.ifPresent(dormitory -> {
            try {
                // 鎸夐渶鍒涘缓Controller瀹炰緥
                DormitoryController dormitoryController = new DormitoryController();
                // 淇濆瓨鍒版暟鎹簱
                boolean success = dormitoryController.addDormitory(dormitory);
                if (success) {
                    showMessage("娣诲姞瀹胯垗鎴愬姛锛?, "success");
                    // 閲嶆柊鍔犺浇鏁版嵁浠ユ樉绀烘渶鏂扮姸鎬?
                    loadDormitories();
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyDormitoryDataChanged("add");
                    logger.info("娣诲姞瀹胯垗鎴愬姛锛歿}", dormitory.getDormitoryId());
                } else {
                    showMessage("娣诲姞瀹胯垗澶辫触锛屽鑸嶅彿鍙兘宸插瓨鍦?, "error");
                }
            } catch (Exception e) {
                logger.error("娣诲姞瀹胯垗澶辫触", e);
                showMessage("娣诲姞瀹胯垗澶辫触锛? + e.getMessage(), "error");
            }
        });
    }

    /**
     * 鏄剧ず缂栬緫瀹胯垗瀵硅瘽妗?
     */
    @FXML
    private void showEditDialog(ActionEvent event) {
        Dormitory selectedDormitory = dormitoryTable.getSelectionModel().getSelectedItem();
        if (selectedDormitory == null) {
            showMessage("璇峰厛閫夋嫨瑕佺紪杈戠殑瀹胯垗锛?, "warning");
            return;
        }

        // 鍒涘缓瀵硅瘽妗?
        Dialog<Dormitory> dialog = new Dialog<>();
        dialog.setTitle("缂栬緫瀹胯垗");
        dialog.setHeaderText("淇敼瀹胯垗淇℃伅");

        // 璁剧疆鎸夐挳
        ButtonType saveButtonType = new ButtonType("淇濆瓨", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField dormIdField = new TextField(selectedDormitory.getDormitoryId());
        dormIdField.setEditable(false); // 瀹胯垗鍙蜂笉鍏佽淇敼
        TextField buildingField = new TextField(selectedDormitory.getBuilding());
        Spinner<Integer> floorSpinner = new Spinner<>(1, 20, selectedDormitory.getFloor());
        floorSpinner.setEditable(true);
        Spinner<Integer> capacitySpinner = new Spinner<>(1, 10, selectedDormitory.getTotalBeds());
        capacitySpinner.setEditable(true);
        TextField managerField = new TextField(selectedDormitory.getManagerName() != null ? selectedDormitory.getManagerName() : "");
        TextField managerPhoneField = new TextField(selectedDormitory.getManagerPhone() != null ? selectedDormitory.getManagerPhone() : "");

        grid.add(new Label("瀹胯垗鍙?"), 0, 0);
        grid.add(dormIdField, 1, 0);
        grid.add(new Label("妤兼爧:"), 0, 1);
        grid.add(buildingField, 1, 1);
        grid.add(new Label("妤煎眰:"), 0, 2);
        grid.add(floorSpinner, 1, 2);
        grid.add(new Label("瀹归噺:"), 0, 3);
        grid.add(capacitySpinner, 1, 3);
        grid.add(new Label("绠＄悊鍛?"), 0, 4);
        grid.add(managerField, 1, 4);
        grid.add(new Label("鑱旂郴鐢佃瘽:"), 0, 5);
        grid.add(managerPhoneField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // 澶勭悊缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                selectedDormitory.setBuilding(buildingField.getText().trim());
                selectedDormitory.setFloor(floorSpinner.getValue());
                selectedDormitory.setTotalBeds(capacitySpinner.getValue());
                selectedDormitory.setManagerName(managerField.getText().trim().isEmpty() ? null : managerField.getText().trim());
                selectedDormitory.setManagerPhone(managerPhoneField.getText().trim().isEmpty() ? null : managerPhoneField.getText().trim());
                return selectedDormitory;
            }
            return null;
        });

        Optional<Dormitory> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                // 鎸夐渶鍒涘缓Controller瀹炰緥
                DormitoryController dormitoryController = new DormitoryController();
                // 淇濆瓨鍒版暟鎹簱
                boolean success = dormitoryController.updateDormitory(selectedDormitory);
                if (success) {
                    showMessage("缂栬緫瀹胯垗鎴愬姛锛?, "success");
                    // 閲嶆柊鍔犺浇鏁版嵁浠ユ樉绀烘渶鏂扮姸鎬?
                    loadDormitories();
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyDormitoryDataChanged("update");
                    logger.info("缂栬緫瀹胯垗鎴愬姛锛歿}", selectedDormitory.getDormitoryId());
                } else {
                    showMessage("缂栬緫瀹胯垗澶辫触", "error");
                }
            } catch (Exception e) {
                logger.error("缂栬緫瀹胯垗澶辫触", e);
                showMessage("缂栬緫瀹胯垗澶辫触锛? + e.getMessage(), "error");
            }
        }
    }

    /**
     * 鍒犻櫎瀹胯垗
     */
    @FXML
    private void deleteDormitory(ActionEvent event) {
        Dormitory selectedDormitory = dormitoryTable.getSelectionModel().getSelectedItem();
        if (selectedDormitory == null) {
            showMessage("璇峰厛閫夋嫨瑕佸垹闄ょ殑瀹胯垗锛?, "warning");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("纭鍒犻櫎");
        alert.setHeaderText("纭畾瑕佸垹闄ゅ鑸嶅悧锛?);
        alert.setContentText("瀹胯垗鍙? " + selectedDormitory.getDormitoryId() + "\n妤兼爧: " + selectedDormitory.getBuilding() +
                           "\n娉ㄦ剰锛氬垹闄ゅ鑸嶅皢鍚屾椂鍒犻櫎璇ュ鑸嶅唴鐨勬墍鏈夊簥浣嶅拰瀛︾敓淇℃伅锛?);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // 鎸夐渶鍒涘缓Controller瀹炰緥
                DormitoryController dormitoryController = new DormitoryController();
                // 浠庢暟鎹簱鍒犻櫎
                boolean success = dormitoryController.deleteDormitory(selectedDormitory.getDormitoryId());
                if (success) {
                    showMessage("鍒犻櫎瀹胯垗鎴愬姛锛?, "success");
                    // 閲嶆柊鍔犺浇鏁版嵁浠ユ樉绀烘渶鏂扮姸鎬?
                    loadDormitories();
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyDormitoryDataChanged("delete");
                    logger.info("鍒犻櫎瀹胯垗鎴愬姛锛歿}", selectedDormitory.getDormitoryId());
                } else {
                    showMessage("鍒犻櫎瀹胯垗澶辫触", "error");
                }
            } catch (Exception e) {
                showMessage("鍒犻櫎瀹胯垗澶辫触锛? + e.getMessage(), "error");
                logger.error("鍒犻櫎瀹胯垗澶辫触", e);
            }
        }
    }

    /**
     * 鍒锋柊瀹胯垗鍒楄〃
     */
    @FXML
    private void refreshDormitories(ActionEvent event) {
        loadDormitories();
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        showMessage(message, "info");
    }

    /**
     * 鏇存柊鏈€鍚庢洿鏂版椂闂?
     */
    private void updateLastUpdateTime() {
        if (lastUpdateLabel != null) {
            lastUpdateLabel.setText("鏈€鍚庢洿鏂? " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
    }

    /**
     * 鏄剧ず娑堟伅锛堝甫绫诲瀷锛?
     */
    private void showMessage(String message, String type) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().removeAll("success", "error", "warning");
        messageLabel.getStyleClass().add(type);

        // 3绉掑悗娓呴櫎娑堟伅
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> {
                    messageLabel.setText("");
                    messageLabel.getStyleClass().removeAll("success", "error", "warning");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

========================================
LoginViewController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dorm.controller.LoginController;
import org.dorm.controller.UserController;
import org.dorm.model.entity.User;
import org.dorm.view.StudentMainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 鐧诲綍鐣岄潰鎺у埗鍣?
 */
public class LoginViewController {
    private static final Logger logger = LoggerFactory.getLogger(LoginViewController.class);

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label messageLabel;

    private org.dorm.controller.LoginController loginBizController = new org.dorm.controller.LoginController();

    @FXML
    private void initialize() {
        // 璁剧疆榛樿鐒︾偣
        usernameField.requestFocus();
    }

    /**
     * 澶勭悊鐧诲綍鎸夐挳鐐瑰嚮浜嬩欢
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // 楠岃瘉杈撳叆
        if (username.isEmpty()) {
            showMessage("璇疯緭鍏ョ敤鎴峰悕锛?);
            usernameField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showMessage("璇疯緭鍏ュ瘑鐮侊紒");
            passwordField.requestFocus();
            return;
        }

        // 绂佺敤鐧诲綍鎸夐挳锛屾樉绀虹櫥褰曚腑
        loginButton.setDisable(true);
        loginButton.setText("鐧诲綍涓?..");
        messageLabel.setText("");

        try {
            // 璋冪敤涓氬姟閫昏緫杩涜鐧诲綍楠岃瘉
            User user = loginBizController.login(username, password);

            if (user != null) {
                logger.info("鐧诲綍鎴愬姛锛岃烦杞埌涓荤晫闈細{} ({})", username, user.getUserType());
                showMessage("鐧诲綍鎴愬姛锛?);

                // 璺宠浆鍒颁富鐣岄潰
                switchToMainView(user);
            } else {
                showMessage("鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒锛?);
                passwordField.clear();
                passwordField.requestFocus();
            }
        } catch (Exception e) {
            logger.error("鐧诲綍杩囩▼涓彂鐢熷紓甯?, e);
            showMessage("鐧诲綍澶辫触锛岃绋嶅悗閲嶈瘯锛?);
        } finally {
            // 鎭㈠鐧诲綍鎸夐挳
            loginButton.setDisable(false);
            loginButton.setText("鐧诲綍");
        }
    }

    /**
     * 鏄剧ず娉ㄥ唽椤甸潰
     */
    @FXML
    private void showRegisterPage(ActionEvent event) {
        try {
            // 鍔犺浇娉ㄥ唽鐣岄潰
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            Parent root = loader.load();

            // 鑾峰彇褰撳墠鑸炲彴
            Stage stage = (Stage) registerButton.getScene().getWindow();

            // 璁剧疆鏂板満鏅?
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.setTitle("瀹胯垗绠＄悊绯荤粺 - 娉ㄥ唽");
            stage.centerOnScreen();

            logger.info("璺宠浆鍒版敞鍐岄〉闈?);
        } catch (IOException e) {
            logger.error("鍔犺浇娉ㄥ唽鐣岄潰澶辫触", e);
            showMessage("鍔犺浇娉ㄥ唽椤甸潰澶辫触锛?);
        }
    }

    /**
     * 澶勭悊閫€鍑烘寜閽偣鍑讳簨浠?
     */
    @FXML
    private void handleExit(ActionEvent event) {
        logger.info("鐢ㄦ埛閫€鍑虹郴缁?);
        System.exit(0);
    }


    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * 鍒囨崲鍒颁富鐣岄潰
     */
    private void switchToMainView(User user) {
        try {
            Parent root;
            String fxmlPath;
            Object controller;

            // 鏍规嵁鐢ㄦ埛绫诲瀷鍔犺浇涓嶅悓鐨勭晫闈?
            logger.info("鍑嗗鍔犺浇鐣岄潰锛岀敤鎴风被鍨嬶細'{}'", user.getUserType());
            if ("瀛︾敓".equals(user.getUserType()) || "学锟斤拷".equals(user.getUserType())) {
                // 瀛︾敓鐣岄潰
                fxmlPath = "/fxml/student_main.fxml";
                logger.info("姝ｅ湪鍔犺浇瀛︾敓鐣岄潰锛歿}", fxmlPath);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                root = loader.load();
                controller = loader.getController();
                logger.info("瀛︾敓鐣岄潰鍔犺浇鎴愬姛锛屽紑濮嬭缃敤鎴凤細{}", user.getUserId());
                ((StudentMainController) controller).setCurrentUser(user);
                logger.info("瀛︾敓鐣岄潰璁剧疆瀹屾垚");
            } else {
                // 绠＄悊鍛樼晫闈?
                fxmlPath = "/fxml/main.fxml";
                logger.info("姝ｅ湪鍔犺浇绠＄悊鍛樼晫闈細{}", fxmlPath);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                root = loader.load();
                controller = loader.getController();
                ((MainController) controller).setCurrentUser(user);
                logger.info("绠＄悊鍛樼晫闈㈣缃畬鎴愶細{}", user.getUserId());
            }

            // 鑾峰彇褰撳墠鑸炲彴
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // 璁剧疆鏂板満鏅?
            Scene scene = new Scene(root, 1400, 900); // 璁剧疆鏇村ぇ鐨勪富鐣岄潰灏哄
            stage.setScene(scene);
            stage.setTitle("瀹胯垗绠＄悊绯荤粺 - 涓荤晫闈?);
            stage.setMinWidth(1200); // 璁剧疆鏈€灏忓搴?
            stage.setMinHeight(800); // 璁剧疆鏈€灏忛珮搴?
            stage.centerOnScreen();

            logger.info("鎴愬姛鍒囨崲鍒颁富鐣岄潰");
        } catch (IOException e) {
            logger.error("鍔犺浇涓荤晫闈㈠け璐?, e);
            showMessage("鍔犺浇涓荤晫闈㈠け璐ワ紝璇烽噸鍚▼搴忥紒");
        }
    }
}

========================================
MainController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.dorm.model.entity.User;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 涓荤晫闈㈡帶鍒跺櫒
 */
public class MainController implements DataUpdateManager.DataUpdateListener {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private Label userInfoLabel;
    @FXML
    private Button logoutButton;

    // 缁熻鏁版嵁鏍囩
    @FXML
    private Label studentCount;
    @FXML
    private Label dormitoryCount;
    @FXML
    private Label bedCount;
    @FXML
    private Label repairCount;
    @FXML
    private Label reminderCount;
    @FXML
    private Label reminderTrend;

    @FXML
    private Button studentManageButton;
    @FXML
    private Button dormManageButton;
    @FXML
    private Button accommodationManageButton;
    @FXML
    private Button repairManageButton;
    @FXML
    private Button paymentManageButton;
    @FXML
    private Button violationManageButton;
    @FXML
    private Button myInfoButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button reminderManageButton;
    @FXML
    private Button changePasswordButton;
    @FXML
    private VBox contentArea;

    // 鍥捐〃缁勪欢
    @FXML
    private BarChart<String, Number> collegeChart;
    @FXML
    private PieChart dormitoryChart;

    private User currentUser;

    // 瀹氭椂鍣紝鐢ㄤ簬瀹氭湡妫€鏌ユ彁閱?    private Timer reminderTimer;

    /**
     * 璁剧疆褰撳墠鐢ㄦ埛淇℃伅
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        updateUserInterface();
        showWelcomeMessage();
        initializeCharts();
    }

    /**
     * 鍒濆鍖栧浘琛ㄦ暟鎹?     */
    private void initializeCharts() {
        try {
            // 浠庢暟鎹簱鑾峰彇瀹炴椂缁熻鏁版嵁
            updateStatisticsFromDatabase();

            // 鍒濆鍖栧闄㈠垎甯冨浘琛?            if (collegeChart != null) {
                updateCollegeChart();
            }

            // 鍒濆鍖栧鑸嶄娇鐢ㄧ巼楗煎浘
            if (dormitoryChart != null) {
                updateDormitoryChart();
            }
        } catch (Exception e) {
            logger.error("鍒濆鍖栧浘琛ㄥけ璐?, e);
        }
    }

    /**
     * 浠庢暟鎹簱鑾峰彇缁熻鏁版嵁
     */
    private void updateStatisticsFromDatabase() {
        try {
            // 鎸夐渶鍒涘缓Controller瀹炰緥锛岄伩鍏嶅湪搴旂敤鍚姩鏃跺氨杩炴帴鏁版嵁搴?            org.dorm.controller.StudentController studentController = new org.dorm.controller.StudentController();
            org.dorm.controller.DormitoryController dormitoryController = new org.dorm.controller.DormitoryController();
            org.dorm.controller.BedController bedController = new org.dorm.controller.BedController();
            org.dorm.controller.RepairApplicationController repairController = new org.dorm.controller.RepairApplicationController();
            org.dorm.controller.ReminderController reminderController = new org.dorm.controller.ReminderController();

            // 浠庢暟鎹簱鑾峰彇瀹炴椂缁熻鏁版嵁
            int totalStudents = studentController.getAllStudents().size();
            int totalDormitories = dormitoryController.getAllDormitories().size();
            int totalBeds = bedController.getAllBeds().size();
            int totalRepairs = repairController.getAllApplications().size();
            int totalReminders = reminderController.getUnprocessedReminders().size();

            // 鏇存柊缁熻鏁板瓧
            if (studentCount != null) studentCount.setText(String.valueOf(totalStudents));
            if (dormitoryCount != null) dormitoryCount.setText(String.valueOf(totalDormitories));
            if (bedCount != null) bedCount.setText(String.valueOf(totalBeds));
            if (repairCount != null) repairCount.setText(String.valueOf(totalRepairs));
            if (reminderCount != null) reminderCount.setText(String.valueOf(totalReminders));
            if (reminderTrend != null) {
                if (totalReminders > 0) {
                    reminderTrend.setText("鏈? + totalReminders + "鏉″緟澶勭悊");
                } else {
                    reminderTrend.setText("鏆傛棤寰呭鐞嗕簨椤?);
                }
            }

            logger.info("鏇存柊棣栭〉缁熻鏁版嵁锛氬鐢焮}浜猴紝瀹胯垗{}鏍嬶紝搴婁綅{}涓紝缁翠慨鐢宠{}涓紝寰呭鐞嗘彁閱抺}鏉?,
                       totalStudents, totalDormitories, totalBeds, totalRepairs, totalReminders);

        } catch (Exception e) {
            logger.error("鑾峰彇缁熻鏁版嵁澶辫触", e);
            // 璁剧疆榛樿鍊?            if (studentCount != null) studentCount.setText("0");
            if (dormitoryCount != null) dormitoryCount.setText("0");
            if (bedCount != null) bedCount.setText("0");
            if (repairCount != null) repairCount.setText("0");
        }
    }

    /**
     * 鏇存柊瀛﹂櫌鍒嗗竷鍥捐〃
     */
    private void updateCollegeChart() {
        if (collegeChart == null) return;

        try {
            collegeChart.getData().clear();

            XYChart.Series<String, Number> collegeSeries = new XYChart.Series<>();
            collegeSeries.setName("瀛︾敓鏁伴噺");

            // 鎸夐渶鍒涘缓Controller瀹炰緥
            org.dorm.controller.StudentController studentController = new org.dorm.controller.StudentController();

            // 浠庢暟鎹簱缁熻鍚勫闄㈠鐢熸暟
            java.util.List<org.dorm.model.entity.Student> students = studentController.getAllStudents();
            java.util.Map<String, Integer> collegeStats = new java.util.HashMap<>();

            // 缁熻鍚勫闄㈠鐢熸暟閲?            for (org.dorm.model.entity.Student student : students) {
                String college = student.getCollegeName();
                if (college != null && !college.trim().isEmpty()) {
                    collegeStats.put(college, collegeStats.getOrDefault(college, 0) + 1);
                }
            }

            // 娣诲姞鏁版嵁鍒板浘琛?            for (java.util.Map.Entry<String, Integer> entry : collegeStats.entrySet()) {
                collegeSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }

            if (!collegeSeries.getData().isEmpty()) {
                collegeChart.getData().add(collegeSeries);
            }

            logger.info("鏇存柊瀛﹂櫌鍒嗗竷鍥捐〃锛歿}涓闄?, collegeStats.size());

        } catch (Exception e) {
            logger.error("鏇存柊瀛﹂櫌鍥捐〃澶辫触", e);
        }
    }

    /**
     * 鏇存柊瀹胯垗浣跨敤鐜囬ゼ鍥?     */
    private void updateDormitoryChart() {
        if (dormitoryChart == null) return;

        try {
            dormitoryChart.getData().clear();

            // 鎸夐渶鍒涘缓Controller瀹炰緥
            org.dorm.controller.DormitoryController dormitoryController = new org.dorm.controller.DormitoryController();
            org.dorm.controller.BedController bedController = new org.dorm.controller.BedController();

            // 浠庢暟鎹簱鑾峰彇瀹胯垗鍜屽簥浣嶆暟鎹?            java.util.List<org.dorm.model.entity.Dormitory> dormitories = dormitoryController.getAllDormitories();
            java.util.List<org.dorm.model.entity.Bed> beds = bedController.getAllBeds();

            // 缁熻鍚勫鑸嶆ゼ鐨勪娇鐢ㄦ儏鍐?            java.util.Map<String, Integer> buildingStats = new java.util.HashMap<>();
            int totalOccupied = 0;
            int totalAvailable = 0;

            // 缁熻姣忎釜瀹胯垗妤肩殑搴婁綅浣跨敤鎯呭喌
            for (org.dorm.model.entity.Dormitory dorm : dormitories) {
                String building = dorm.getBuilding();
                if (building != null && !building.trim().isEmpty()) {
                    buildingStats.put(building, buildingStats.getOrDefault(building, 0) + dorm.getTotalBeds());
                }
            }

            // 缁熻鍗犵敤鍜岀┖闂插簥浣?            for (org.dorm.model.entity.Bed bed : beds) {
                if ("鍗犵敤".equals(bed.getStatus())) {
                    totalOccupied++;
                } else if ("绌洪棽".equals(bed.getStatus())) {
                    totalAvailable++;
                }
            }

            // 娣诲姞鏁版嵁鍒伴ゼ鍥?            if (totalOccupied > 0) {
                dormitoryChart.getData().add(new PieChart.Data("宸插崰鐢ㄥ簥浣?(" + totalOccupied + "涓?", totalOccupied));
            }
            if (totalAvailable > 0) {
                dormitoryChart.getData().add(new PieChart.Data("绌洪棽搴婁綅 (" + totalAvailable + "涓?", totalAvailable));
            }

            // 濡傛灉娌℃湁鍏蜂綋鏁版嵁锛岃嚦灏戞樉绀轰竴涓粯璁ら」
            if (dormitoryChart.getData().isEmpty()) {
                dormitoryChart.getData().add(new PieChart.Data("鏆傛棤鏁版嵁", 1));
            }

            logger.info("鏇存柊瀹胯垗浣跨敤鎯呭喌鍥捐〃锛氬崰鐢▄}涓紝绌洪棽{}涓?, totalOccupied, totalAvailable);

        } catch (Exception e) {
            logger.error("鏇存柊瀹胯垗鍥捐〃澶辫触", e);
            // 娣诲姞閿欒鏁版嵁
            dormitoryChart.getData().clear();
            dormitoryChart.getData().add(new PieChart.Data("鏁版嵁鍔犺浇澶辫触", 1));
        }
    }

    @FXML
    private void initialize() {
        // 娉ㄥ唽鏁版嵁鏇存柊鐩戝惉鍣?        DataUpdateManager.getInstance().addListener(this);

        // 鍚姩鎻愰啋瀹氭椂妫€鏌ヤ换鍔?        startReminderCheckTimer();

        logger.info("涓荤晫闈㈡帶鍒跺櫒鍒濆鍖栧畬鎴愶紝宸叉敞鍐屾暟鎹洿鏂扮洃鍚櫒鍜屾彁閱掑畾鏃舵鏌?);
    }

    /**
     * 鍚姩鎻愰啋瀹氭椂妫€鏌ヤ换鍔?     * 姣?鍒嗛挓妫€鏌ヤ竴娆℃湭缂磋垂鍜屾湭澶勭悊杩濈邯璁板綍
     */
    private void startReminderCheckTimer() {
        reminderTimer = new Timer("ReminderCheckTimer", true);

        // 绔嬪嵆鎵ц涓€娆℃鏌?        reminderTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkAndGenerateReminders();
            }
        }, 0);

        // 姣?鍒嗛挓鎵ц涓€娆℃鏌?(5 * 60 * 1000 姣)
        reminderTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkAndGenerateReminders();
            }
        }, 5 * 60 * 1000, 5 * 60 * 1000);

        logger.info("鎻愰啋瀹氭椂妫€鏌ヤ换鍔″凡鍚姩锛屾瘡5鍒嗛挓妫€鏌ヤ竴娆?);
    }

    /**
     * 妫€鏌ュ苟鐢熸垚鎻愰啋
     */
    private void checkAndGenerateReminders() {
        try {
            org.dorm.controller.ReminderController reminderController = new org.dorm.controller.ReminderController();

            // 妫€鏌ユ湭缂磋垂瀛︾敓
            int unpaidCount = reminderController.checkUnpaidStudents();

            // 妫€鏌ユ湭澶勭悊杩濈邯璁板綍
            int violationCount = reminderController.checkUnprocessedViolations();

            if (unpaidCount > 0 || violationCount > 0) {
                logger.info("瀹氭椂妫€鏌ュ畬鎴愶紝鐢熸垚浜唟}鏉＄即璐规彁閱掑拰{}鏉¤繚绾彁閱?,
                           unpaidCount, violationCount);

                // 鍦↗avaFX绾跨▼涓洿鏂癠I
                javafx.application.Platform.runLater(() -> {
                    updateStatisticsFromDatabase();
                });
            }

        } catch (Exception e) {
            logger.error("瀹氭椂妫€鏌ユ彁閱掑け璐?, e);
        }
    }

    /**
     * 鍋滄瀹氭椂鍣紙鍦ㄥ簲鐢ㄥ叧闂椂璋冪敤锛?     */
    public void stopReminderTimer() {
        if (reminderTimer != null) {
            reminderTimer.cancel();
            reminderTimer.purge();
            logger.info("鎻愰啋瀹氭椂妫€鏌ヤ换鍔″凡鍋滄");
        }
    }

    /**
     * 瀹炵幇DataUpdateListener鎺ュ彛 - 澶勭悊鏁版嵁鏇存柊閫氱煡
     */
    @Override
    public void onDataUpdated(String dataType, String operation) {
        logger.info("鏀跺埌鏁版嵁鏇存柊閫氱煡: {} - {}", dataType, operation);

        // 鏍规嵁鏁版嵁绫诲瀷鍒锋柊鐩稿叧缁熻鏁版嵁
        switch (dataType) {
            case "student":
            case "dormitory":
            case "payment":
            case "violation":
            case "repair":
                // 浠讳綍鏁版嵁鍙樻洿閮藉彲鑳藉奖鍝嶇粺璁℃暟鎹紝鍒锋柊鎵€鏈夌粺璁?                updateStatisticsFromDatabase();
                updateCollegeChart();
                updateDormitoryChart();
                break;
            default:
                logger.warn("鏈煡鐨勬暟鎹被鍨? {}", dataType);
        }
    }


    /**
     * 鏇存柊鐢ㄦ埛鐣岄潰鏄剧ず
     */
    private void updateUserInterface() {
        if (currentUser != null) {
            String userType = currentUser.getUserType();
            String userTypeText = "admin".equals(userType) ? "绠＄悊鍛? : "student".equals(userType) ? "瀛︾敓" : userType;
            userInfoLabel.setText("褰撳墠鐢ㄦ埛锛? + currentUser.getUserId() + " (" + userTypeText + ")");

            // 鏍规嵁鐢ㄦ埛绫诲瀷鏄剧ず涓嶅悓鐨勮彍鍗?            if ("瀛︾敓".equals(userType)) {
                // 瀛︾敓鍙互鐪嬪埌缁翠慨绠＄悊銆佺即璐圭鐞嗐€佽繚绾鐞嗐€佹垜鐨勪俊鎭拰淇敼瀵嗙爜
                studentManageButton.setVisible(false);
                dormManageButton.setVisible(false);
                accommodationManageButton.setVisible(false);
                statisticsButton.setVisible(false);
                repairManageButton.setVisible(true);
                paymentManageButton.setVisible(true);
                violationManageButton.setVisible(true);
                myInfoButton.setVisible(true);
                changePasswordButton.setVisible(true);
            } else {
                // 绠＄悊鍛樺彲浠ョ湅鍒版墍鏈夊姛鑳?                studentManageButton.setVisible(true);
                dormManageButton.setVisible(true);
                accommodationManageButton.setVisible(true);
                statisticsButton.setVisible(true);
                repairManageButton.setVisible(true);
                paymentManageButton.setVisible(true);
                violationManageButton.setVisible(true);
                myInfoButton.setVisible(false);
                changePasswordButton.setVisible(true);
            }

            // 鍒锋柊棣栭〉缁熻鏁版嵁
            updateStatisticsFromDatabase();
            updateCollegeChart();
            updateDormitoryChart();
        }
    }

    /**
     * 鏄剧ず娆㈣繋淇℃伅
     */
    private void showWelcomeMessage() {
        // 涓婚〉鐜板湪浣跨敤鏂扮殑缁熻鍗＄墖甯冨眬锛屼笉闇€瑕佹樉绀烘杩庢枃鏈?        logger.info("鐢ㄦ埛 {} 鐧诲綍鎴愬姛锛屾樉绀轰富椤?, currentUser != null ? currentUser.getUserId() : "unknown");
    }

    /**
     * 澶勭悊閫€鍑虹櫥褰?     */
    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            logger.info("鐢ㄦ埛閫€鍑虹櫥褰曪細{}", currentUser != null ? currentUser.getUserId() : "鏈煡鐢ㄦ埛");

            // 鍔犺浇鐧诲綍鐣岄潰
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            // 鑾峰彇褰撳墠鑸炲彴
            Stage stage = (Stage) logoutButton.getScene().getWindow();

            // 璁剧疆鏂板満鏅?            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("瀹胯垗绠＄悊绯荤粺");
            stage.centerOnScreen();

        } catch (IOException e) {
            logger.error("鍔犺浇鐧诲綍鐣岄潰澶辫触", e);
        }
    }

    /**
     * 鏄剧ず瀛︾敓绠＄悊鐣岄潰
     */
    @FXML
    private void showStudentManagement(ActionEvent event) {
        loadContent("/fxml/student_management.fxml", "瀛︾敓绠＄悊");
    }

    /**
     * 鏄剧ず瀹胯垗绠＄悊鐣岄潰
     */
    @FXML
    private void showDormitoryManagement(ActionEvent event) {
        loadContent("/fxml/dormitory_management.fxml", "瀹胯垗绠＄悊");
    }

    /**
     * 鏄剧ず浣忓鍒嗛厤鐣岄潰
     */
    @FXML
    private void showAccommodationManagement(ActionEvent event) {
        loadContent("/fxml/accommodation_management.fxml", "浣忓鍒嗛厤");
    }

    /**
     * 鏄剧ず缂磋垂绠＄悊鐣岄潰
     */
    @FXML
    private void showPaymentManagement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/payment_management.fxml"));
            Parent root = loader.load();

            PaymentManagementController controller = loader.getController();

            // 濡傛灉鏄鐢熺敤鎴凤紝璁剧疆涓哄鐢熸ā寮?            if ("瀛︾敓".equals(currentUser.getUserType())) {
                controller.setStudentMode(currentUser.getUserId());
            }

            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);

            logger.info("鏄剧ず缂磋垂绠＄悊鐣岄潰");
        } catch (IOException e) {
            logger.error("鍔犺浇缂磋垂绠＄悊鐣岄潰澶辫触", e);
            showError("鍔犺浇缂磋垂绠＄悊鐣岄潰澶辫触锛? + e.getMessage());
        }
    }


    /**
     * 鏄剧ず杩濈邯绠＄悊鐣岄潰
     */
    @FXML
    private void showViolationManagement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/violation_management.fxml"));
            Parent root = loader.load();

            ViolationManagementController controller = loader.getController();

            // 濡傛灉鏄鐢熺敤鎴凤紝璁剧疆涓哄鐢熸ā寮?            if ("瀛︾敓".equals(currentUser.getUserType())) {
                controller.setStudentMode(currentUser.getUserId());
            }

            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);

            logger.info("鏄剧ず杩濈邯绠＄悊鐣岄潰");
        } catch (IOException e) {
            logger.error("鍔犺浇杩濈邯绠＄悊鐣岄潰澶辫触", e);
            showError("鍔犺浇杩濈邯绠＄悊鐣岄潰澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 鏄剧ず缁翠慨绠＄悊鐣岄潰
     */
    @FXML
    private void showRepairManagement(ActionEvent event) {
        if ("student".equals(currentUser.getUserType())) {
            loadContent("/fxml/student_repair.fxml", "鎴戠殑缁翠慨鐢宠");
        } else {
            loadContent("/fxml/admin_repair.fxml", "缁翠慨绠＄悊");
        }
    }

    /**
     * 鏄剧ず缁熻鏌ヨ鐣岄潰
     */
    @FXML
    private void showStatistics(ActionEvent event) {
        loadContent("/fxml/statistics.fxml", "缁熻鏌ヨ");
    }

    /**
     * 鏄剧ず鎻愰啋绠＄悊鐣岄潰
     */
    @FXML
    private void showReminderManagement(ActionEvent event) {
        loadContent("/fxml/reminder_management_new.fxml", "鎻愰啋绠＄悊");
    }

    /**
     * 鏄剧ず鎴戠殑淇℃伅鐣岄潰
     */
    @FXML
    private void showMyInfo(ActionEvent event) {
        try {
            // 鍔犺浇瀛︾敓涓汉淇℃伅鐣岄潰
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student_info.fxml"));
            Parent root = loader.load();

            StudentInfoController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);

            logger.info("鏄剧ず鎴戠殑淇℃伅鐣岄潰");

        } catch (IOException e) {
            logger.error("鍔犺浇鎴戠殑淇℃伅鐣岄潰澶辫触", e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("閿欒");
            alert.setHeaderText("鍔犺浇鎴戠殑淇℃伅鐣岄潰澶辫触");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * 鏄剧ず淇敼瀵嗙爜鐣岄潰
     */
    @FXML
    private void showChangePassword(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/change_password.fxml"));
            Parent root = loader.load();

            // 鑾峰彇淇敼瀵嗙爜鎺у埗鍣ㄥ苟浼犻€掑綋鍓嶇敤鎴蜂俊鎭?            ChangePasswordController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("淇敼瀵嗙爜");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            logger.error("鍔犺浇淇敼瀵嗙爜鐣岄潰澶辫触", e);
        }
    }



    /**
     * 鍔犺浇鍐呭鍖哄煙
     */
    private void loadContent(String fxmlPath, String title) {
        try {
            // #region agent log
            logDebugEvent("MainController.java:514", "寮€濮嬪姞杞界晫闈?, java.util.Map.of("fxmlPath", fxmlPath, "title", title), "A");
            // #endregion

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();

            // #region agent log
            logDebugEvent("MainController.java:521", "FXML鍔犺浇鎴愬姛", java.util.Map.of("fxmlPath", fxmlPath, "controller", loader.getController().getClass().getSimpleName()), "A");
            // #endregion

            // JavaFX浼氳嚜鍔ㄨ皟鐢ˊFXML娉ㄨВ鐨刬nitialize鏂规硶锛屾棤闇€鎵嬪姩璋冪敤

            contentArea.getChildren().clear();
            contentArea.getChildren().add(content);

            // #region agent log
            logDebugEvent("MainController.java:528", "鐣岄潰鍔犺浇瀹屾垚", java.util.Map.of("title", title), "A");
            // #endregion

            logger.info("鎴愬姛鍔犺浇鐣岄潰锛歿}", title);

        } catch (IOException e) {
            // #region agent log
            logDebugEvent("MainController.java:535", "鍔犺浇鐣岄潰澶辫触", java.util.Map.of("fxmlPath", fxmlPath, "error", e.getMessage()), "A");
            // #endregion

            logger.error("鍔犺浇鐣岄潰澶辫触锛歿}", fxmlPath, e);
            showError("鍔犺浇鐣岄潰澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 璁板綍璋冭瘯浜嬩欢
     */
    private void logDebugEvent(String location, String message, java.util.Map<String, Object> data, String hypothesisId) {
        try {
            String logEntry = String.format("{\"id\":\"log_%d_%s\",\"timestamp\":%d,\"location\":\"%s\",\"message\":\"%s\",\"data\":%s,\"sessionId\":\"debug-session\",\"runId\":\"initial-test\",\"hypothesisId\":\"%s\"}",
                System.currentTimeMillis(),
                java.util.UUID.randomUUID().toString().substring(0, 6),
                System.currentTimeMillis(),
                location,
                message,
                data.toString().replace("=", "\":\"").replace("{", "{\"").replace("}", "\"}").replace(", ", "\",\""),
                hypothesisId
            );
            java.nio.file.Files.writeString(
                java.nio.file.Paths.get("c:\\Users\\浠讳竾鍗歕\Desktop\\璇捐\\dormitory-management-system\\.cursor\\debug.log"),
                logEntry + "\n",
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND
            );
        } catch (Exception e) {
            logger.warn("璁板綍璋冭瘯鏃ュ織澶辫触", e);
        }
    }

    /**
     * 鏄剧ず閿欒淇℃伅
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("閿欒");
        alert.setHeaderText("鎿嶄綔澶辫触");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 蹇€熸搷浣滐細娣诲姞瀛︾敓
     */
    @FXML
    private void showAddStudent(ActionEvent event) {
        showStudentManagement(event);
    }

    /**
     * 蹇€熸搷浣滐細娣诲姞瀹胯垗
     */
    @FXML
    private void showAddDormitory(ActionEvent event) {
        showDormitoryManagement(event);
    }

    /**
     * 蹇€熸搷浣滐細缁翠慨鐢宠
     */
    @FXML
    private void showRepairApplication(ActionEvent event) {
        showRepairManagement(event);
    }

    /**
     * 蹇€熸搷浣滐細鏌ョ湅鎶ヨ〃
     */
}

========================================
PaymentManagementController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.dorm.controller.PaymentController;
import org.dorm.controller.StudentController;
import org.dorm.model.entity.Payment;
import org.dorm.model.entity.Student;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * 缂磋垂绠＄悊鐣岄潰鎺у埗鍣?
 */
public class PaymentManagementController {

    private boolean isStudentMode = false;
    private String currentStudentId;

    /**
     * 璁剧疆涓哄鐢熸ā寮忥紝鍙樉绀烘寚瀹氬鐢熺殑缂磋垂璁板綍
     */
    public void setStudentMode(String studentId) {
        this.isStudentMode = true;
        this.currentStudentId = studentId;

        // 鍦ㄥ鐢熸ā寮忎笅闅愯棌绠＄悊鍔熻兘
        if (addButton != null) addButton.setVisible(false);
        if (editButton != null) editButton.setVisible(false);
        if (deleteButton != null) deleteButton.setVisible(false);

        // 閲嶆柊鍔犺浇鏁版嵁
        loadPayments();
    }
    private static final Logger logger = LoggerFactory.getLogger(PaymentManagementController.class);

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> searchTypeCombo;
    @FXML
    private Label statusLabel;
    @FXML
    private Label selectedLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label lastUpdateLabel;
    @FXML
    private TableView<Payment> paymentTable;
    @FXML
    private TableColumn<Payment, String> paymentIdColumn;
    @FXML
    private TableColumn<Payment, String> studentIdColumn;
    @FXML
    private TableColumn<Payment, String> studentNameColumn;
    @FXML
    private TableColumn<Payment, String> amountColumn;
    @FXML
    private TableColumn<Payment, String> paymentDateColumn;
    @FXML
    private TableColumn<Payment, String> semesterColumn;
    @FXML
    private TableColumn<Payment, String> statusColumn;

    // 鍒嗛〉鐩稿叧
    @FXML
    private Button firstPageButton;
    @FXML
    private Button prevPageButton;
    @FXML
    private Button nextPageButton;
    @FXML
    private Button lastPageButton;
    @FXML
    private Label pageInfoLabel;
    @FXML
    private ComboBox<String> pageSizeCombo;

    private ObservableList<Payment> paymentData = FXCollections.observableArrayList();
    private PaymentController paymentController = new PaymentController();
    private StudentController studentController = new StudentController();

    // 鍒嗛〉鐩稿叧鍙橀噺
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalPages = 1;
    private int totalRecords = 0;

    @FXML
    private void initialize() {
        // 鍒濆鍖栬〃鏍煎垪
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        // 瀛︾敓濮撳悕鍒楅渶瑕佺壒娈婂鐞嗭紝閫氳繃瀛﹀彿鏌ユ壘濮撳悕
        studentNameColumn.setCellValueFactory(cellData -> {
            Payment payment = cellData.getValue();
            try {
                Student student = studentController.getStudentById(payment.getStudentId());
                return new javafx.beans.property.SimpleStringProperty(
                    student != null ? student.getName() : "鏈煡");
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty("鏈煡");
            }
        });

        // 閲戦鍒楅渶瑕佹牸寮忓寲鏄剧ず
        amountColumn.setCellValueFactory(cellData -> {
            Payment payment = cellData.getValue();
            java.math.BigDecimal amount = payment.getAmount();
            if (amount != null) {
                return new javafx.beans.property.SimpleStringProperty(String.format("%.2f", amount.doubleValue()));
            } else {
                return new javafx.beans.property.SimpleStringProperty("0.00");
            }
        });

        // 缂磋垂鏃ユ湡鏍煎紡鍖?
        paymentDateColumn.setCellValueFactory(cellData -> {
            Payment payment = cellData.getValue();
            java.util.Date date = payment.getPaymentDate();
            if (date != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return new javafx.beans.property.SimpleStringProperty(sdf.format(date));
            } else {
                return new javafx.beans.property.SimpleStringProperty("");
            }
        });

        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // 璁剧疆琛ㄦ牸鏁版嵁
        paymentTable.setItems(paymentData);

        // 鍒濆鍖栨悳绱㈢被鍨?
        if (searchTypeCombo != null) {
            searchTypeCombo.setValue("鍏ㄩ儴");
        }

        // 鍒濆鍖栭〉闈㈠ぇ灏?
        if (pageSizeCombo != null) {
            pageSizeCombo.setValue("10");
        }

        // 鍒濆鍖栬〃鏍奸€夋嫨鐩戝惉鍣?
        if (paymentTable != null) {
            paymentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                updateSelectedCount();
            });
        }

        // 鍒濆鍖栧垎椤垫寜閽姸鎬?
        updatePaginationButtons();

        // 鍔犺浇鍒濆鏁版嵁
        loadPayments();

        logger.info("缂磋垂绠＄悊鐣岄潰鍒濆鍖栧畬鎴?);
    }

    /**
     * 鍔犺浇缂磋垂鏁版嵁
     */
    private void loadPayments() {
        try {
            List<Payment> payments;

            if (isStudentMode && currentStudentId != null) {
                // 瀛︾敓妯″紡锛氬彧鍔犺浇褰撳墠瀛︾敓鐨勭即璐硅褰?
                payments = paymentController.getPaymentsByStudentId(currentStudentId);
            } else {
                // 绠＄悊鍛樻ā寮忥細鍔犺浇鎵€鏈夌即璐硅褰?
                payments = paymentController.getAllPayments();
            }

            paymentData.clear();
            paymentData.addAll(payments);

            totalRecords = payments.size();
            totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            // 鏇存柊鐘舵€佹樉绀?
            statusLabel.setText("鍏?" + totalRecords + " 鏉¤褰?);
            updateLastUpdateTime();
            updatePageInfo();
            showMessage("鏁版嵁鍔犺浇瀹屾垚", "success");

            logger.info("鍔犺浇缂磋垂鏁版嵁鎴愬姛锛歿}鏉¤褰?, payments.size());

        } catch (Exception e) {
            logger.error("鍔犺浇缂磋垂鏁版嵁澶辫触", e);
            showMessage("鍔犺浇鏁版嵁澶辫触锛? + e.getMessage(), "error");
        }
    }

    /**
     * 鏇存柊鍒嗛〉鎸夐挳鐘舵€?
     */
    private void updatePaginationButtons() {
        // 鏆傛椂绂佺敤鎵€鏈夊垎椤垫寜閽紝鍥犱负鍒嗛〉鍔熻兘杩樻湭瀹屽叏瀹炵幇
        if (firstPageButton != null) firstPageButton.setDisable(true);
        if (prevPageButton != null) prevPageButton.setDisable(true);
        if (nextPageButton != null) nextPageButton.setDisable(true);
        if (lastPageButton != null) lastPageButton.setDisable(true);
    }

    /**
     * 鏇存柊椤甸潰淇℃伅
     */
    private void updatePageInfo() {
        if (pageInfoLabel != null) {
            pageInfoLabel.setText("绗?" + currentPage + " 椤?/ 鍏?" + Math.max(1, totalPages) + " 椤?);
        }
    }

    /**
     * 鏇存柊鏈€鍚庢洿鏂版椂闂?
     */
    private void updateLastUpdateTime() {
        if (lastUpdateLabel != null) {
            lastUpdateLabel.setText("鏈€鍚庢洿鏂? " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
    }

    /**
     * 鏇存柊閫変腑椤硅鏁?
     */
    private void updateSelectedCount() {
        if (paymentTable != null && selectedLabel != null) {
            int selectedCount = paymentTable.getSelectionModel().getSelectedItems().size();
            selectedLabel.setText("宸查€夋嫨 " + selectedCount + " 椤?);
        }
    }

    /**
     * 鏄剧ず娣诲姞缂磋垂璁板綍瀵硅瘽妗?
     */
    @FXML
    private void showAddDialog(ActionEvent event) {
        // 鍒涘缓瀵硅瘽妗?
        Dialog<Payment> dialog = new Dialog<>();
        dialog.setTitle("娣诲姞缂磋垂璁板綍");
        dialog.setHeaderText("璇疯緭鍏ユ柊鐨勭即璐硅褰曚俊鎭?);

        // 璁剧疆鎸夐挳
        ButtonType saveButtonType = new ButtonType("淇濆瓨", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // 琛ㄥ崟鎺т欢
        ComboBox<String> studentCombo = new ComboBox<>();
        TextField amountField = new TextField();
        DatePicker paymentDatePicker = new DatePicker();
        TextField semesterField = new TextField();
        ComboBox<String> statusCombo = new ComboBox<>();

        // 鍒濆鍖栧鐢熶笅鎷夋
        try {
            List<Student> students = studentController.getAllStudents();
            for (Student student : students) {
                studentCombo.getItems().add(student.getStudentId() + " - " + student.getName());
            }
        } catch (Exception e) {
            logger.error("鍔犺浇瀛︾敓鍒楄〃澶辫触", e);
        }

        // 鍒濆鍖栫姸鎬佷笅鎷夋
        statusCombo.getItems().addAll("鏈即", "宸茬即");
        statusCombo.setValue("鏈即");

        // 璁剧疆瀛︽湡榛樿鍊?
        semesterField.setText("2024-2025瀛﹀勾绗竴瀛︽湡");

        // 璁剧疆鏃ユ湡榛樿鍊间负浠婂ぉ
        paymentDatePicker.setValue(java.time.LocalDate.now());

        grid.add(new Label("瀛︾敓:"), 0, 0);
        grid.add(studentCombo, 1, 0);
        grid.add(new Label("缂磋垂閲戦:"), 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(new Label("缂磋垂鏃ユ湡:"), 0, 2);
        grid.add(paymentDatePicker, 1, 2);
        grid.add(new Label("缂磋垂瀛︽湡:"), 0, 3);
        grid.add(semesterField, 1, 3);
        grid.add(new Label("缂磋垂鐘舵€?"), 0, 4);
        grid.add(statusCombo, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // 杞崲缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    Payment payment = new Payment();

                    // 鐢熸垚缂磋垂鍗曞彿
                    String paymentId = "PAY" + System.currentTimeMillis();
                    payment.setPaymentId(paymentId);

                    // 瑙ｆ瀽瀛︾敓ID
                    String selectedStudent = studentCombo.getValue();
                    if (selectedStudent != null && selectedStudent.contains(" - ")) {
                        String studentId = selectedStudent.split(" - ")[0];
                        payment.setStudentId(studentId);
                    }

                    // 璁剧疆鍏朵粬瀛楁
                    if (!amountField.getText().isEmpty()) {
                        payment.setAmount(java.math.BigDecimal.valueOf(Double.parseDouble(amountField.getText())));
                    }
                    payment.setPaymentDate(java.sql.Date.valueOf(paymentDatePicker.getValue()));
                    payment.setSemester(semesterField.getText());
                    payment.setStatus(statusCombo.getValue());

                    return payment;
                } catch (Exception e) {
                    logger.error("鍒涘缓缂磋垂璁板綍澶辫触", e);
                    return null;
                }
            }
            return null;
        });

        Optional<Payment> result = dialog.showAndWait();
        result.ifPresent(payment -> {
            try {
                boolean success = paymentController.addPayment(payment);
                if (success) {
                    showMessage("缂磋垂璁板綍娣诲姞鎴愬姛锛?);
                    loadPayments();
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyPaymentDataChanged("add");
                    logger.info("娣诲姞缂磋垂璁板綍鎴愬姛锛歿}", payment.getPaymentId());
                } else {
                    showMessage("缂磋垂璁板綍娣诲姞澶辫触锛?);
                }
            } catch (Exception e) {
                logger.error("娣诲姞缂磋垂璁板綍寮傚父", e);
                showMessage("娣诲姞澶辫触锛? + e.getMessage());
            }
        });
    }

    /**
     * 鏄剧ず缂栬緫缂磋垂璁板綍瀵硅瘽妗?
     */
    @FXML
    private void showEditDialog(ActionEvent event) {
        Payment selectedPayment = paymentTable.getSelectionModel().getSelectedItem();
        if (selectedPayment == null) {
            showMessage("璇峰厛閫夋嫨瑕佺紪杈戠殑缂磋垂璁板綍锛?);
            return;
        }

        // 鍒涘缓瀵硅瘽妗?
        Dialog<Payment> dialog = new Dialog<>();
        dialog.setTitle("缂栬緫缂磋垂璁板綍");
        dialog.setHeaderText("淇敼缂磋垂璁板綍淇℃伅");

        // 璁剧疆鎸夐挳
        ButtonType saveButtonType = new ButtonType("淇濆瓨", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // 琛ㄥ崟鎺т欢
        Label studentLabel = new Label(selectedPayment.getStudentId() + " - " +
            (studentController.getStudentById(selectedPayment.getStudentId()) != null ?
             studentController.getStudentById(selectedPayment.getStudentId()).getName() : "鏈煡"));
        TextField amountField = new TextField(String.valueOf(selectedPayment.getAmount()));
        DatePicker paymentDatePicker = new DatePicker();
        TextField semesterField = new TextField(selectedPayment.getSemester());
        ComboBox<String> statusCombo = new ComboBox<>();

        // 璁剧疆鏃ユ湡
        if (selectedPayment.getPaymentDate() != null) {
            paymentDatePicker.setValue(selectedPayment.getPaymentDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        } else {
            paymentDatePicker.setValue(java.time.LocalDate.now());
        }

        // 鍒濆鍖栫姸鎬佷笅鎷夋
        statusCombo.getItems().addAll("鏈即", "宸茬即");
        statusCombo.setValue(selectedPayment.getStatus());

        grid.add(new Label("瀛︾敓:"), 0, 0);
        grid.add(studentLabel, 1, 0);
        grid.add(new Label("缂磋垂閲戦:"), 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(new Label("缂磋垂鏃ユ湡:"), 0, 2);
        grid.add(paymentDatePicker, 1, 2);
        grid.add(new Label("缂磋垂瀛︽湡:"), 0, 3);
        grid.add(semesterField, 1, 3);
        grid.add(new Label("缂磋垂鐘舵€?"), 0, 4);
        grid.add(statusCombo, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // 杞崲缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    Payment payment = new Payment();
                    payment.setPaymentId(selectedPayment.getPaymentId());
                    payment.setStudentId(selectedPayment.getStudentId());

                    if (!amountField.getText().isEmpty()) {
                        payment.setAmount(java.math.BigDecimal.valueOf(Double.parseDouble(amountField.getText())));
                    }
                    payment.setPaymentDate(java.sql.Date.valueOf(paymentDatePicker.getValue()));
                    payment.setSemester(semesterField.getText());
                    payment.setStatus(statusCombo.getValue());

                    return payment;
                } catch (Exception e) {
                    logger.error("鏇存柊缂磋垂璁板綍澶辫触", e);
                    return null;
                }
            }
            return null;
        });

        Optional<Payment> result = dialog.showAndWait();
        result.ifPresent(payment -> {
            try {
                boolean success = paymentController.updatePayment(payment);
                if (success) {
                    showMessage("缂磋垂璁板綍鏇存柊鎴愬姛锛?);
                    loadPayments();
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyPaymentDataChanged("update");
                    logger.info("鏇存柊缂磋垂璁板綍鎴愬姛锛歿}", payment.getPaymentId());
                } else {
                    showMessage("缂磋垂璁板綍鏇存柊澶辫触锛?);
                }
            } catch (Exception e) {
                logger.error("鏇存柊缂磋垂璁板綍寮傚父", e);
                showMessage("鏇存柊澶辫触锛? + e.getMessage());
            }
        });
    }

    /**
     * 鍒犻櫎缂磋垂璁板綍
     */
    @FXML
    private void deletePayment(ActionEvent event) {
        Payment selectedPayment = paymentTable.getSelectionModel().getSelectedItem();
        if (selectedPayment == null) {
            showMessage("璇峰厛閫夋嫨瑕佸垹闄ょ殑缂磋垂璁板綍锛?);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("纭鍒犻櫎");
        alert.setHeaderText("纭畾瑕佸垹闄よ繖鏉＄即璐硅褰曞悧锛?);
        alert.setContentText("缂磋垂鍗曞彿: " + selectedPayment.getPaymentId() + "\n瀛︾敓: " + selectedPayment.getStudentId());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean success = paymentController.deletePayment(selectedPayment.getPaymentId());
                if (success) {
                    showMessage("缂磋垂璁板綍鍒犻櫎鎴愬姛锛?);
                    loadPayments();
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyPaymentDataChanged("delete");
                    logger.info("鍒犻櫎缂磋垂璁板綍鎴愬姛锛歿}", selectedPayment.getPaymentId());
                } else {
                    showMessage("缂磋垂璁板綍鍒犻櫎澶辫触锛?);
                }
            } catch (Exception e) {
                logger.error("鍒犻櫎缂磋垂璁板綍寮傚父", e);
                showMessage("鍒犻櫎澶辫触锛? + e.getMessage());
            }
        }
    }

    /**
     * 鍒锋柊缂磋垂璁板綍鍒楄〃
     */
    @FXML
    private void refreshPayments() {
        loadPayments();
    }

    /**
     * 鎼滅储缂磋垂璁板綍
     */
    @FXML
    private void searchPayments(ActionEvent event) {
        String searchText = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            loadPayments(); // 濡傛灉鎼滅储涓虹┖锛屾樉绀烘墍鏈夎褰?
            return;
        }

        try {
            List<Payment> allPayments = paymentController.getAllPayments();
            List<Payment> filteredPayments;

            switch (searchType) {
                case "瀛﹀彿":
                    filteredPayments = allPayments.stream()
                        .filter(p -> p.getStudentId().contains(searchText))
                        .toList();
                    break;
                case "濮撳悕":
                    filteredPayments = allPayments.stream()
                        .filter(p -> {
                            try {
                                Student student = studentController.getStudentById(p.getStudentId());
                                return student != null && student.getName().contains(searchText);
                            } catch (Exception e) {
                                return false;
                            }
                        })
                        .toList();
                    break;
                case "瀛︽湡":
                    filteredPayments = allPayments.stream()
                        .filter(p -> p.getSemester() != null && p.getSemester().contains(searchText))
                        .toList();
                    break;
                default:
                    // 鍏ㄩ儴鎼滅储
                    filteredPayments = allPayments.stream()
                        .filter(p -> p.getStudentId().contains(searchText) ||
                                   p.getSemester() != null && p.getSemester().contains(searchText) ||
                                   p.getStatus() != null && p.getStatus().contains(searchText))
                        .toList();
                    break;
            }

            paymentData.clear();
            paymentData.addAll(filteredPayments);
            statusLabel.setText("鎼滅储鍒?" + filteredPayments.size() + " 鏉¤褰?);
            showMessage("鎼滅储瀹屾垚", "success");

        } catch (Exception e) {
            logger.error("鎼滅储缂磋垂璁板綍澶辫触", e);
            showMessage("鎼滅储澶辫触锛? + e.getMessage(), "error");
        }
    }

    // 鍒嗛〉鐩稿叧鏂规硶锛堟殏鏃舵湭瀹炵幇锛?
    @FXML
    private void goToFirstPage() {}
    @FXML
    private void goToPrevPage() {}
    @FXML
    private void goToNextPage() {}
    @FXML
    private void goToLastPage() {}
    @FXML
    private void changePageSize(ActionEvent event) {}

    /**
     * 鏄剧ず娑堟伅锛堝甫绫诲瀷锛?
     */
    private void showMessage(String message, String type) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().removeAll("success", "error", "warning");
        messageLabel.getStyleClass().add(type);

        // 3绉掑悗娓呴櫎娑堟伅
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> {
                    messageLabel.setText("灏辩华");
                    messageLabel.getStyleClass().removeAll("success", "error", "warning");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        showMessage(message, "info");
    }
}

========================================
RegisterViewController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dorm.controller.UserController;
import org.dorm.controller.StudentController;
import org.dorm.model.entity.User;
import org.dorm.model.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 娉ㄥ唽鐣岄潰鎺у埗鍣?
 */
public class RegisterViewController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterViewController.class);

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private RadioButton adminRadioButton;
    @FXML
    private RadioButton studentRadioButton;
    @FXML
    private ToggleGroup userTypeGroup;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;
    @FXML
    private Label messageLabel;

    private UserController userController = new UserController();
    private StudentController studentController = new StudentController();

    @FXML
    private void initialize() {
        // 璁剧疆榛樿鐒︾偣
        usernameField.requestFocus();
    }

    /**
     * 澶勭悊娉ㄥ唽鎸夐挳鐐瑰嚮浜嬩欢
     */
    @FXML
    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        // 楠岃瘉杈撳叆
        if (username.isEmpty()) {
            showMessage("璇疯緭鍏ョ敤鎴峰悕锛?);
            usernameField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showMessage("璇疯緭鍏ュ瘑鐮侊紒");
            passwordField.requestFocus();
            return;
        }

        if (password.length() < 6) {
            showMessage("瀵嗙爜闀垮害鑷冲皯6浣嶏紒");
            passwordField.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            showMessage("涓ゆ杈撳叆鐨勫瘑鐮佷笉涓€鑷达紒");
            confirmPasswordField.clear();
            confirmPasswordField.requestFocus();
            return;
        }

        String userType = getSelectedUserType();

        // 绂佺敤娉ㄥ唽鎸夐挳锛屾樉绀烘敞鍐屼腑
        registerButton.setDisable(true);
        registerButton.setText("鍒涘缓涓?..");
        messageLabel.setText("");

        try {
            // 鍒涘缓鏂扮敤鎴?
            User newUser = new User();
            newUser.setUserId(username);
            newUser.setPassword(password);
            newUser.setUserType(userType);

            // 璋冪敤涓氬姟閫昏緫杩涜娉ㄥ唽
            boolean success = userController.addUser(newUser);

            if (success) {
                // 濡傛灉鏄鐢熺敤鎴凤紝鍚屾椂鍒涘缓瀛︾敓璁板綍
                if ("瀛︾敓".equals(userType)) {
                    try {
                        Student newStudent = new Student();
                        newStudent.setStudentId(username);
                        newStudent.setPassword(password);
                        newStudent.setName(""); // 濮撳悕鏆傛椂涓虹┖锛岄渶瑕佸悗缁畬鍠勪俊鎭?
                        newStudent.setCollegeName(""); // 瀛﹂櫌鏆傛椂涓虹┖
                        newStudent.setClassName(""); // 鐝骇鏆傛椂涓虹┖
                        newStudent.setGender(""); // 鎬у埆鏆傛椂涓虹┖
                        newStudent.setEmail(username + "@nuc.edu.cn"); // 榛樿閭
                        newStudent.setPhone(""); // 鐢佃瘽鏆傛椂涓虹┖
                        // bedId鏆傛椂涓虹┖锛岄渶瑕佸悗缁垎閰?

                        boolean studentSuccess = studentController.addStudent(newStudent);
                        if (!studentSuccess) {
                            logger.warn("瀛︾敓璁板綍鍒涘缓澶辫触锛屼絾鐢ㄦ埛璐︽埛宸插垱寤猴細{}", username);
                            // 鐢ㄦ埛璐︽埛宸插垱寤猴紝杩欓噷涓嶅奖鍝嶆敞鍐屾垚鍔?
                        }
                    } catch (Exception e) {
                        logger.error("鍒涘缓瀛︾敓璁板綍鏃跺彂鐢熷紓甯革紝浣嗙敤鎴疯处鎴峰凡鍒涘缓锛歿}", username, e);
                        // 鐢ㄦ埛璐︽埛宸插垱寤猴紝杩欓噷涓嶅奖鍝嶆敞鍐屾垚鍔?
                    }
                }

                logger.info("娉ㄥ唽鎴愬姛锛歿} ({})", username, userType);
                showMessage("璐﹀彿鍒涘缓鎴愬姛锛佽杩斿洖鐧诲綍椤甸潰浣跨敤鏂拌处鍙风櫥褰曘€? + ("瀛︾敓".equals(userType) ? "\n璇峰湪鐧诲綍鍚庡畬鍠勬偍鐨勪釜浜轰俊鎭€? : ""));

                // 娓呯┖琛ㄥ崟
                usernameField.clear();
                passwordField.clear();
                confirmPasswordField.clear();
            } else {
                showMessage("娉ㄥ唽澶辫触锛岀敤鎴峰悕鍙兘宸插瓨鍦紒");
                usernameField.requestFocus();
            }
        } catch (Exception e) {
            logger.error("娉ㄥ唽杩囩▼涓彂鐢熷紓甯?, e);
            showMessage("娉ㄥ唽澶辫触锛岃绋嶅悗閲嶈瘯锛?);
        } finally {
            // 鎭㈠娉ㄥ唽鎸夐挳
            registerButton.setDisable(false);
            registerButton.setText("鍒涘缓璐﹀彿");
        }
    }

    /**
     * 杩斿洖鐧诲綍椤甸潰
     */
    @FXML
    private void backToLogin(ActionEvent event) {
        try {
            // 鍔犺浇鐧诲綍鐣岄潰
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            // 鑾峰彇褰撳墠鑸炲彴
            Stage stage = (Stage) backButton.getScene().getWindow();

            // 璁剧疆鏂板満鏅?
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.setTitle("瀹胯垗绠＄悊绯荤粺 - 鐧诲綍");
            stage.centerOnScreen();

            logger.info("杩斿洖鐧诲綍椤甸潰");
        } catch (IOException e) {
            logger.error("鍔犺浇鐧诲綍鐣岄潰澶辫触", e);
            showMessage("杩斿洖鐧诲綍椤甸潰澶辫触锛?);
        }
    }

    /**
     * 鑾峰彇閫変腑鐨勭敤鎴风被鍨?
     */
    private String getSelectedUserType() {
        if (adminRadioButton != null && adminRadioButton.isSelected()) {
            return "绠＄悊鍛?;
        } else if (studentRadioButton != null && studentRadioButton.isSelected()) {
            return "瀛︾敓";
        }
        return "绠＄悊鍛?; // 榛樿閫夋嫨绠＄悊鍛?
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
        // 娣诲姞鏍峰紡绫?
        messageLabel.getStyleClass().removeAll("success", "error", "warning");
        if (message.contains("鎴愬姛")) {
            messageLabel.getStyleClass().add("success");
        } else {
            messageLabel.getStyleClass().add("error");
        }
    }
}

========================================
ReminderManagementController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.dorm.controller.ReminderController;
import org.dorm.model.entity.Reminder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * 鎻愰啋绠＄悊鐣岄潰鎺у埗鍣? */
public class ReminderManagementController {
    private static final Logger logger = LoggerFactory.getLogger(ReminderManagementController.class);

    // 鎺у埗鍣?    private ReminderController reminderController = new ReminderController();

    // 鍒嗛〉鐩稿叧
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalRecords = 0;
    private int totalPages = 1;

    // 宸ュ叿鏍?    @FXML private Button refreshButton;
    @FXML private Button checkAllButton;
    @FXML private Button markProcessedButton;
    @FXML private Button deleteProcessedButton;
    @FXML private TextField searchField;
    @FXML private ComboBox<String> searchTypeCombo;
    @FXML private Button searchButton;
    @FXML private Label statusLabel;
    @FXML private Label selectedLabel;

    // 琛ㄦ牸
    @FXML private TableView<Reminder> reminderTable;
    @FXML private TableColumn<Reminder, String> reminderIdColumn;
    @FXML private TableColumn<Reminder, String> studentIdColumn;
    @FXML private TableColumn<Reminder, String> studentNameColumn;
    @FXML private TableColumn<Reminder, String> typeColumn;
    @FXML private TableColumn<Reminder, String> titleColumn;
    @FXML private TableColumn<Reminder, String> priorityColumn;
    @FXML private TableColumn<Reminder, String> statusColumn;
    @FXML private TableColumn<Reminder, String> createTimeColumn;

    // 鍒嗛〉鎺т欢
    @FXML private Button firstPageButton;
    @FXML private Button prevPageButton;
    @FXML private Label pageInfoLabel;
    @FXML private Button nextPageButton;
    @FXML private Button lastPageButton;
    @FXML private ComboBox<String> pageSizeCombo;

    // 璇︽儏鍖哄煙
    @FXML private TextArea reminderDetailArea;

    private ObservableList<Reminder> reminderData = FXCollections.observableArrayList();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @FXML
    private void initialize() {
        logger.info("鍒濆鍖栨彁閱掔鐞嗙晫闈?..");

        // 鍒濆鍖栬〃鏍煎垪
        reminderIdColumn.setCellValueFactory(new PropertyValueFactory<>("reminderId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        createTimeColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getCreateTime() != null) {
                return new javafx.beans.property.SimpleStringProperty(
                    dateFormat.format(cellData.getValue().getCreateTime()));
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });

        // 璁剧疆琛ㄦ牸鏁版嵁
        reminderTable.setItems(reminderData);

        // 鍒濆鍖栦笅鎷夋
        searchTypeCombo.setValue("鍏ㄩ儴");
        pageSizeCombo.setValue("10");

        // 娣诲姞琛ㄦ牸閫夋嫨鐩戝惉鍣?        reminderTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    showReminderDetail(newSelection);
                    updateSelectedLabel();
                }
            });

        // 鍔犺浇鍒濆鏁版嵁
        refreshReminders();

        logger.info("鎻愰啋绠＄悊鐣岄潰鍒濆鍖栧畬鎴?);
    }

    /**
     * 鍒锋柊鎻愰啋鍒楄〃
     */
    @FXML
    private void refreshReminders() {
        try {
            logger.info("鍒锋柊鎻愰啋鍒楄〃...");

            // 鑾峰彇鎵€鏈夋彁閱?            List<Reminder> reminders = reminderController.getAllReminders();
            logger.info("浠庢暟鎹簱鑾峰彇鍒?{} 鏉℃彁閱掕褰?, reminders.size());

            // 鏇存柊鏁版嵁
            reminderData.clear();
            reminderData.addAll(reminders);

            // 鏇存柊鐘舵€佷俊鎭?            totalRecords = reminders.size();
            updateStatusLabels();
            updatePagination();

            // 璋冭瘯淇℃伅
            if (reminders.isEmpty()) {
                showMessage("鎻愰啋鍒楄〃涓虹┖锛岃妫€鏌ユ暟鎹簱杩炴帴鎴栬繍琛屾暟鎹垵濮嬪寲鑴氭湰");
                logger.warn("鎻愰啋鍒楄〃涓虹┖锛屾鏌ユ暟鎹簱涓殑鎻愰啋鏁版嵁");
            } else {
                showMessage("鎻愰啋鍒楄〃鍒锋柊瀹屾垚锛屽叡" + reminders.size() + "鏉¤褰?);
                logger.info("鎻愰啋鍒楄〃鍒锋柊瀹屾垚锛屽叡{}鏉¤褰?, reminders.size());
            }

        } catch (Exception e) {
            logger.error("鍒锋柊鎻愰啋鍒楄〃澶辫触", e);
            showError("鍒锋柊澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 妫€鏌ユ墍鏈夋彁閱掞紙閲嶆柊鐢熸垚锛?     */
    @FXML
    private void checkAllReminders() {
        try {
            logger.info("寮€濮嬫鏌ユ墍鏈夋彁閱?..");

            // 棣栧厛妫€鏌ユ暟鎹簱杩炴帴
            try {
                org.dorm.util.DatabaseUtil.getConnection();
                logger.info("鏁版嵁搴撹繛鎺ユ甯?);
            } catch (Exception dbEx) {
                logger.error("鏁版嵁搴撹繛鎺ュけ璐?, dbEx);
                showError("鏁版嵁搴撹繛鎺ュけ璐ワ細" + dbEx.getMessage());
                return;
            }

            // 鎵ц瀹屾暣鐨勬彁閱掓鏌?            int totalReminders = reminderController.refreshAllReminders();

            // 鍒锋柊鏄剧ず
            refreshReminders();

            showMessage("鎻愰啋妫€鏌ュ畬鎴愶紝鍏辩敓鎴? + totalReminders + "鏉℃柊鎻愰啋");

            logger.info("鎻愰啋妫€鏌ュ畬鎴愶紝鍏辩敓鎴恵}鏉℃柊鎻愰啋", totalReminders);

        } catch (Exception e) {
            logger.error("妫€鏌ユ彁閱掑け璐?, e);
            showError("妫€鏌ュけ璐ワ細" + e.getMessage());
        }
    }

    /**
     * 鏍囪涓哄凡澶勭悊
     */
    @FXML
    private void markAsProcessed() {
        Reminder selectedReminder = reminderTable.getSelectionModel().getSelectedItem();
        if (selectedReminder == null) {
            showError("璇峰厛閫夋嫨瑕佸鐞嗙殑鎻愰啋");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("纭澶勭悊");
        alert.setHeaderText("纭畾瑕佹爣璁拌繖鏉℃彁閱掍负宸插鐞嗗悧锛?);
        alert.setContentText("鎻愰啋ID: " + selectedReminder.getReminderId() + "\n瀛︾敓: " + selectedReminder.getStudentName());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // 鏇存柊鎻愰啋鐘舵€?                selectedReminder.setStatus("宸插鐞?);
                selectedReminder.setHandleTime(new java.util.Date());
                selectedReminder.setHandler("绠＄悊鍛?); // 搴旇浠庡綋鍓嶇櫥褰曠敤鎴疯幏鍙?
                boolean success = reminderController.updateReminder(selectedReminder);
                if (success) {
                    refreshReminders();
                    showMessage("鎻愰啋宸叉爣璁颁负宸插鐞?);
                    logger.info("鎻愰啋{}宸叉爣璁颁负宸插鐞?, selectedReminder.getReminderId());
                } else {
                    showError("鏍囪澶勭悊澶辫触");
                }

            } catch (Exception e) {
                logger.error("鏍囪鎻愰啋澶勭悊澶辫触", e);
                showError("鎿嶄綔澶辫触锛? + e.getMessage());
            }
        }
    }

    /**
     * 鍒犻櫎宸插鐞嗙殑鎻愰啋
     */
    @FXML
    private void deleteProcessed() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("纭鍒犻櫎");
        alert.setHeaderText("纭畾瑕佸垹闄ゆ墍鏈夊凡澶勭悊鐨勬彁閱掑悧锛?);
        alert.setContentText("姝ゆ搷浣滀笉鍙仮澶?);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                int deletedCount = reminderController.deleteProcessedReminders();

                if (deletedCount > 0) {
                    refreshReminders();
                    showMessage("鎴愬姛鍒犻櫎浜? + deletedCount + "鏉″凡澶勭悊鐨勬彁閱?);
                    logger.info("鍒犻櫎浜唟}鏉″凡澶勭悊鐨勬彁閱?, deletedCount);
                } else {
                    showMessage("娌℃湁宸插鐞嗙殑鎻愰啋闇€瑕佸垹闄?);
                }

            } catch (Exception e) {
                logger.error("鍒犻櫎宸插鐞嗘彁閱掑け璐?, e);
                showError("鍒犻櫎澶辫触锛? + e.getMessage());
            }
        }
    }

    /**
     * 鎼滅储鎻愰啋
     */
    @FXML
    private void searchReminders() {
        String searchText = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            refreshReminders();
            return;
        }

        try {
            logger.info("鎼滅储鎻愰啋锛氱被鍨?{}, 鍏抽敭璇?{}", searchType, searchText);

            List<Reminder> allReminders = reminderController.getAllReminders();
            reminderData.clear();

            for (Reminder reminder : allReminders) {
                boolean matches = false;

                switch (searchType) {
                    case "鍏ㄩ儴":
                        matches = reminder.getStudentId().contains(searchText) ||
                                 reminder.getStudentName().contains(searchText) ||
                                 reminder.getType().contains(searchText) ||
                                 reminder.getTitle().contains(searchText) ||
                                 reminder.getContent().contains(searchText);
                        break;
                    case "瀛﹀彿":
                        matches = reminder.getStudentId().contains(searchText);
                        break;
                    case "濮撳悕":
                        matches = reminder.getStudentName().contains(searchText);
                        break;
                    case "绫诲瀷":
                        matches = reminder.getType().contains(searchText);
                        break;
                }

                if (matches) {
                    reminderData.add(reminder);
                }
            }

            totalRecords = reminderData.size();
            updateStatusLabels();
            updatePagination();

            showMessage("鎼滅储瀹屾垚锛屾壘鍒? + reminderData.size() + "鏉¤褰?);

        } catch (Exception e) {
            logger.error("鎼滅储鎻愰啋澶辫触", e);
            showError("鎼滅储澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 鏄剧ず鎻愰啋璇︽儏
     */
    private void showReminderDetail(Reminder reminder) {
        if (reminderDetailArea != null && reminder != null) {
            StringBuilder detail = new StringBuilder();
            detail.append("鎻愰啋ID: ").append(reminder.getReminderId()).append("\n");
            detail.append("瀛︾敓: ").append(reminder.getStudentName()).append(" (").append(reminder.getStudentId()).append(")\n");
            detail.append("绫诲瀷: ").append(reminder.getType()).append("\n");
            detail.append("鏍囬: ").append(reminder.getTitle()).append("\n");
            detail.append("浼樺厛绾? ").append(reminder.getPriority()).append("\n");
            detail.append("鐘舵€? ").append(reminder.getStatus()).append("\n");
            detail.append("鍒涘缓鏃堕棿: ").append(dateFormat.format(reminder.getCreateTime())).append("\n");

            if (reminder.getHandleTime() != null) {
                detail.append("澶勭悊鏃堕棿: ").append(dateFormat.format(reminder.getHandleTime())).append("\n");
                detail.append("澶勭悊浜? ").append(reminder.getHandler()).append("\n");
            }

            detail.append("\n璇︾粏鍐呭:\n").append(reminder.getContent());

            reminderDetailArea.setText(detail.toString());
        }
    }

    /**
     * 鍒嗛〉鐩稿叧鏂规硶
     */
    @FXML private void goToFirstPage() { currentPage = 1; updatePagination(); }
    @FXML private void goToPrevPage() { if (currentPage > 1) currentPage--; updatePagination(); }
    @FXML private void goToNextPage() { if (currentPage < totalPages) currentPage++; updatePagination(); }
    @FXML private void goToLastPage() { currentPage = totalPages; updatePagination(); }

    @FXML
    private void changePageSize() {
        try {
            pageSize = Integer.parseInt(pageSizeCombo.getValue());
            currentPage = 1;
            updatePagination();
        } catch (NumberFormatException e) {
            logger.error("鏃犳晥鐨勯〉闈㈠ぇ灏?, e);
        }
    }

    /**
     * 鏇存柊鍒嗛〉鏄剧ず
     */
    private void updatePagination() {
        totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (currentPage > totalPages) currentPage = totalPages;
        if (currentPage < 1) currentPage = 1;

        pageInfoLabel.setText("绗?" + currentPage + " 椤?/ 鍏?" + totalPages + " 椤?);

        // 鏇存柊鎸夐挳鐘舵€?        firstPageButton.setDisable(currentPage <= 1);
        prevPageButton.setDisable(currentPage <= 1);
        nextPageButton.setDisable(currentPage >= totalPages);
        lastPageButton.setDisable(currentPage >= totalPages);
    }

    /**
     * 鏇存柊鐘舵€佹爣绛?     */
    private void updateStatusLabels() {
        if (statusLabel != null) {
            statusLabel.setText("鍏?" + totalRecords + " 鏉¤褰?);
        }
        updateSelectedLabel();
    }

    /**
     * 鏇存柊閫夋嫨鐘舵€佹爣绛?     */
    private void updateSelectedLabel() {
        if (selectedLabel != null) {
            int selectedCount = reminderTable.getSelectionModel().getSelectedItems().size();
            selectedLabel.setText("宸查€夋嫨 " + selectedCount + " 椤?);
        }
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("鎻愮ず");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 鏄剧ず閿欒娑堟伅
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("閿欒");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

========================================
StatisticsController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dorm.controller.StudentController;
import org.dorm.controller.DormitoryController;
import org.dorm.controller.RepairApplicationController;
import org.dorm.controller.PaymentController;
import org.dorm.controller.ViolationController;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.Dormitory;
import org.dorm.model.entity.RepairApplication;
import org.dorm.model.entity.Payment;
import org.dorm.model.entity.Violation;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 缁熻鏌ヨ鐣岄潰鎺у埗鍣? */
public class StatisticsController implements DataUpdateManager.DataUpdateListener {
    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    // 鎺у埗鍣?    private StudentController studentController = new StudentController();
    private DormitoryController dormitoryController = new DormitoryController();
    private RepairApplicationController repairController = new RepairApplicationController();
    private org.dorm.controller.PaymentController paymentController = new org.dorm.controller.PaymentController();
    private org.dorm.controller.ViolationController violationController = new org.dorm.controller.ViolationController();

    // 缁熻姒傝鍗＄墖
    @FXML private Label totalStudentsLabel;
    @FXML private Label totalDormitoriesLabel;
    @FXML private Label totalRepairsLabel;
    @FXML private Label paymentRateLabel;
    @FXML private Label studentsChangeLabel;
    @FXML private Label dormitoriesChangeLabel;
    @FXML private Label repairsChangeLabel;
    @FXML private Label paymentChangeLabel;

    // 宸ュ叿鏍?    @FXML private Button refreshButton;
    @FXML private Button exportButton;
    @FXML private ComboBox<String> timeRangeCombo;
    @FXML private ComboBox<String> dataTypeCombo;

    // 鍥捐〃缁勪欢
    @FXML private PieChart collegeChart;
    @FXML private BarChart<String, Number> dormitoryChart;
    @FXML private LineChart<String, Number> repairTrendChart;
    @FXML private PieChart paymentChart;

    // 璇︾粏鏁版嵁琛ㄦ牸
    @FXML private TableView<org.dorm.view.StatisticsData> detailedTable;
    @FXML private TableColumn<org.dorm.view.StatisticsData, String> categoryColumn;
    @FXML private TableColumn<org.dorm.view.StatisticsData, String> nameColumn;
    @FXML private TableColumn<org.dorm.view.StatisticsData, Integer> countColumn;
    @FXML private TableColumn<org.dorm.view.StatisticsData, Double> percentageColumn;
    @FXML private TableColumn<org.dorm.view.StatisticsData, String> statusColumn;

    // 杩斿洖鎸夐挳
    @FXML private Button backToMainButton;

    // 鐘舵€佹爮
    @FXML private Label lastUpdateLabel;
    @FXML private Label dataSourceLabel;

    private ObservableList<org.dorm.view.StatisticsData> tableData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        logger.info("鍒濆鍖栫粺璁＄晫闈?..");

        // 鍒濆鍖栬〃鏍煎垪
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // 璁剧疆琛ㄦ牸鏁版嵁
        detailedTable.setItems(tableData);

        // 鍒濆鍖栦笅鎷夋
        timeRangeCombo.setValue("鍏ㄩ儴鏃堕棿");
        dataTypeCombo.setValue("缁煎悎缁熻");

        // 鍔犺浇鍒濆缁熻鏁版嵁
        refreshStatistics();

        // 娉ㄥ唽鏁版嵁鏇存柊鐩戝惉鍣?        DataUpdateManager.getInstance().addListener(this);

        logger.info("缁熻鐣岄潰鍒濆鍖栧畬鎴愶紝宸叉敞鍐屾暟鎹洿鏂扮洃鍚櫒");
    }

    /**
     * 鍒锋柊缁熻鏁版嵁
     */
    @FXML
    private void refreshStatistics() {
        try {
            logger.info("寮€濮嬪埛鏂扮粺璁℃暟鎹?..");

            // 鏇存柊姒傝鍗＄墖
            updateOverviewCards();

            // 鏇存柊鍥捐〃
            updateCharts();

            // 鏇存柊璇︾粏琛ㄦ牸
            updateDetailedTable();

            // 鏇存柊鏈€鍚庢洿鏂版椂闂?            updateLastUpdateTime();

            showMessage("鏁版嵁鍒锋柊瀹屾垚");

            logger.info("缁熻鏁版嵁鍒锋柊瀹屾垚");

        } catch (Exception e) {
            logger.error("鍒锋柊缁熻鏁版嵁澶辫触", e);
            showError("鍒锋柊鏁版嵁澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 杩斿洖涓荤晫闈?     */
    @FXML
    private void backToMain(ActionEvent event) {
        try {
            // 鍔犺浇涓荤晫闈?            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();

            // 鑾峰彇褰撳墠鑸炲彴
            Stage stage = (Stage) backToMainButton.getScene().getWindow();

            // 璁剧疆鏂板満鏅?            Scene scene = new Scene(root, 1400, 900);
            stage.setScene(scene);
            stage.setTitle("瀹胯垗绠＄悊绯荤粺 - 涓荤晫闈?);
            stage.centerOnScreen();

            logger.info("杩斿洖涓荤晫闈㈡垚鍔?);

        } catch (Exception e) {
            logger.error("杩斿洖涓荤晫闈㈠け璐?, e);
            showError("杩斿洖涓荤晫闈㈠け璐ワ細" + e.getMessage());
        }
    }

    /**
     * 瀵煎嚭鏁版嵁
     */
    @FXML
    private void exportData(ActionEvent event) {
        showMessage("瀵煎嚭鍔熻兘寮€鍙戜腑...");
    }

    /**
     * 鏇存柊姒傝鍗＄墖
     */
    private void updateOverviewCards() {
        try {
            // 鑾峰彇瀛︾敓鎬绘暟
            List<Student> students = studentController.getAllStudents();
            int totalStudents = students.size();
            totalStudentsLabel.setText(String.valueOf(totalStudents));
            studentsChangeLabel.setText("杈冧笂鏈?+0");

            // 鑾峰彇瀹胯垗鎬绘暟
            List<Dormitory> dormitories = dormitoryController.getAllDormitories();
            int totalDormitories = dormitories.size();
            totalDormitoriesLabel.setText(String.valueOf(totalDormitories));
            dormitoriesChangeLabel.setText("杈冧笂鏈?+0");

            // 鑾峰彇缁翠慨鐢宠鎬绘暟
            List<RepairApplication> repairs = repairController.getAllApplications();
            int totalRepairs = repairs.size();
            totalRepairsLabel.setText(String.valueOf(totalRepairs));
            repairsChangeLabel.setText("杈冧笂鏈?+0");

            // 璁＄畻缂磋垂鐜?(浠庢暟鎹簱鑾峰彇鐪熷疄鏁版嵁)
            try {
                List<org.dorm.model.entity.Payment> payments = paymentController.getAllPayments();
                int totalPayments = payments.size();
                int paidPayments = 0;
                for (org.dorm.model.entity.Payment payment : payments) {
                    if ("宸茬即".equals(payment.getStatus())) {
                        paidPayments++;
                    }
                }
                double paymentRate = totalPayments > 0 ? (double) paidPayments / totalPayments * 100 : 0;
                paymentRateLabel.setText(String.format("%.1f%%", paymentRate));
                paymentChangeLabel.setText("鐩爣 95%");
            } catch (Exception e) {
                logger.error("璁＄畻缂磋垂鐜囧け璐?, e);
                paymentRateLabel.setText("0%");
                paymentChangeLabel.setText("鐩爣 95%");
            }

        } catch (Exception e) {
            logger.error("鏇存柊姒傝鍗＄墖澶辫触", e);
        }
    }

    /**
     * 鏇存柊鍥捐〃
     */
    private void updateCharts() {
        updateCollegeChart();
        updateDormitoryChart();
        updateRepairTrendChart();
        updatePaymentChart();
    }

    /**
     * 鏇存柊瀛﹂櫌鍒嗗竷鍥捐〃
     */
    private void updateCollegeChart() {
        try {
            List<Student> students = studentController.getAllStudents();
            Map<String, Integer> collegeStats = new HashMap<>();

            // 缁熻鍚勫闄㈠鐢熸暟閲?            for (Student student : students) {
                String college = student.getCollegeName();
                collegeStats.put(college, collegeStats.getOrDefault(college, 0) + 1);
            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry : collegeStats.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }

            collegeChart.setData(pieChartData);
            collegeChart.setTitle("瀛︾敓瀛﹂櫌鍒嗗竷");

        } catch (Exception e) {
            logger.error("鏇存柊瀛﹂櫌鍒嗗竷鍥捐〃澶辫触", e);
        }
    }

    /**
     * 鏇存柊瀹胯垗浣跨敤鎯呭喌鍥捐〃
     */
    private void updateDormitoryChart() {
        try {
            List<Dormitory> dormitories = dormitoryController.getAllDormitories();
            List<Student> students = studentController.getAllStudents();

            // 缁熻鍚勫鑸嶆ゼ鐨勫鐢熸暟閲?            Map<String, Integer> dormStats = new HashMap<>();
            for (Student student : students) {
                if (student.getBedId() != null && !student.getBedId().isEmpty()) {
                    // 浠庡簥浣岻D涓彁鍙栧鑸岻D (鍋囪鏍煎紡涓?"A101-01")
                    String dormId = student.getBedId().split("-")[0];
                    dormStats.put(dormId, dormStats.getOrDefault(dormId, 0) + 1);
                }
            }

            XYChart.Series<String, Number> occupiedSeries = new XYChart.Series<>();
            occupiedSeries.setName("宸插叆浣?);
            XYChart.Series<String, Number> capacitySeries = new XYChart.Series<>();
            capacitySeries.setName("鎬诲簥浣?);

            for (Dormitory dorm : dormitories) {
                String dormId = dorm.getDormitoryId();
                int occupied = dormStats.getOrDefault(dormId, 0);
                int capacity = dorm.getTotalBeds();

                occupiedSeries.getData().add(new XYChart.Data<>(dormId, occupied));
                capacitySeries.getData().add(new XYChart.Data<>(dormId, capacity));
            }

            dormitoryChart.getData().clear();
            dormitoryChart.getData().addAll(occupiedSeries, capacitySeries);
            dormitoryChart.setTitle("瀹胯垗浣跨敤鎯呭喌 (鍏ヤ綇/瀹归噺)");

        } catch (Exception e) {
            logger.error("鏇存柊瀹胯垗浣跨敤鎯呭喌鍥捐〃澶辫触", e);
        }
    }

    /**
     * 鏇存柊缁翠慨鐢宠瓒嬪娍鍥捐〃
     */
    private void updateRepairTrendChart() {
        try {
            List<RepairApplication> repairs = repairController.getAllApplications();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("缁翠慨鐢宠鏁伴噺");

            // 鎸夌姸鎬佺粺璁＄淮淇敵璇?            Map<String, Integer> statusStats = new HashMap<>();
            for (RepairApplication repair : repairs) {
                String status = repair.getStatus();
                statusStats.put(status, statusStats.getOrDefault(status, 0) + 1);
            }

            // 娣诲姞涓嶅悓鐘舵€佺殑鏁版嵁
            series.getData().add(new XYChart.Data<>("寰呭彈鐞?, statusStats.getOrDefault("pending", 0)));
            series.getData().add(new XYChart.Data<>("宸插彈鐞?, statusStats.getOrDefault("accepted", 0)));
            series.getData().add(new XYChart.Data<>("缁翠慨涓?, statusStats.getOrDefault("repairing", 0)));
            series.getData().add(new XYChart.Data<>("宸插畬鎴?, statusStats.getOrDefault("completed", 0)));

            repairTrendChart.getData().clear();
            repairTrendChart.getData().add(series);
            repairTrendChart.setTitle("缁翠慨鐢宠鐘舵€佺粺璁?(" + repairs.size() + "绗旂敵璇?");

        } catch (Exception e) {
            logger.error("鏇存柊缁翠慨鐢宠瓒嬪娍鍥捐〃澶辫触", e);
        }
    }

    /**
     * 鏇存柊缂磋垂鎯呭喌鍥捐〃
     */
    private void updatePaymentChart() {
        try {
            List<org.dorm.model.entity.Payment> payments = paymentController.getAllPayments();
            int paidCount = 0;
            int unpaidCount = 0;

            for (org.dorm.model.entity.Payment payment : payments) {
                if ("宸茬即".equals(payment.getStatus())) {
                    paidCount++;
                } else {
                    unpaidCount++;
                }
            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            if (paidCount > 0) {
                pieChartData.add(new PieChart.Data("宸茬即璐?(" + paidCount + ")", paidCount));
            }
            if (unpaidCount > 0) {
                pieChartData.add(new PieChart.Data("鏈即璐?(" + unpaidCount + ")", unpaidCount));
            }

            paymentChart.setData(pieChartData);
            paymentChart.setTitle("缂磋垂鎯呭喌缁熻 (" + payments.size() + "绗旇褰?");

        } catch (Exception e) {
            logger.error("鏇存柊缂磋垂鎯呭喌鍥捐〃澶辫触", e);
        }
    }

    /**
     * 鏇存柊璇︾粏鏁版嵁琛ㄦ牸
     */
    private void updateDetailedTable() {
        try {
            tableData.clear();

            // 娣诲姞瀛︾敓缁熻鏁版嵁
            List<Student> students = studentController.getAllStudents();
            Map<String, Integer> collegeCount = new HashMap<>();
            for (Student student : students) {
                collegeCount.put(student.getCollegeName(), collegeCount.getOrDefault(student.getCollegeName(), 0) + 1);
            }

            int totalStudents = students.size();
            for (Map.Entry<String, Integer> entry : collegeCount.entrySet()) {
                double percentage = totalStudents > 0 ? (double) entry.getValue() / totalStudents * 100 : 0;
                tableData.add(new org.dorm.view.StatisticsData("瀛︾敓", entry.getKey(), entry.getValue(), percentage, "姝ｅ父"));
            }

            // 娣诲姞瀹胯垗缁熻鏁版嵁
            List<Dormitory> dormitories = dormitoryController.getAllDormitories();
            for (Dormitory dorm : dormitories) {
                tableData.add(new org.dorm.view.StatisticsData("瀹胯垗", dorm.getDormitoryId(), dorm.getTotalBeds(), 100.0, "鍚敤"));
            }

            // 娣诲姞缂磋垂缁熻鏁版嵁
            try {
                List<org.dorm.model.entity.Payment> payments = paymentController.getAllPayments();
                int paidCount = 0;
                int unpaidCount = 0;
                for (org.dorm.model.entity.Payment payment : payments) {
                    if ("宸茬即".equals(payment.getStatus())) {
                        paidCount++;
                    } else {
                        unpaidCount++;
                    }
                }
                if (paidCount > 0) {
                    tableData.add(new org.dorm.view.StatisticsData("缂磋垂", "宸茬即璐?, paidCount, 100.0 * paidCount / payments.size(), "姝ｅ父"));
                }
                if (unpaidCount > 0) {
                    tableData.add(new org.dorm.view.StatisticsData("缂磋垂", "鏈即璐?, unpaidCount, 100.0 * unpaidCount / payments.size(), "寰呭鐞?));
                }
            } catch (Exception e) {
                logger.error("鑾峰彇缂磋垂缁熻鏁版嵁澶辫触", e);
            }

            // 娣诲姞杩濈邯缁熻鏁版嵁
            try {
                List<org.dorm.model.entity.Violation> violations = violationController.getAllViolations();
                Map<String, Integer> penaltyStats = new HashMap<>();
                for (org.dorm.model.entity.Violation violation : violations) {
                    String penalty = violation.getPenalty();
                    penaltyStats.put(penalty, penaltyStats.getOrDefault(penalty, 0) + 1);
                }

                for (Map.Entry<String, Integer> entry : penaltyStats.entrySet()) {
                    tableData.add(new org.dorm.view.StatisticsData("杩濈邯", entry.getKey(), entry.getValue(), 100.0 * entry.getValue() / violations.size(), "宸插鐞?));
                }
            } catch (Exception e) {
                logger.error("鑾峰彇杩濈邯缁熻鏁版嵁澶辫触", e);
            }

        } catch (Exception e) {
            logger.error("鏇存柊璇︾粏鏁版嵁琛ㄦ牸澶辫触", e);
        }
    }

    /**
     * 鏇存柊鏈€鍚庢洿鏂版椂闂?     */
    private void updateLastUpdateTime() {
        if (lastUpdateLabel != null) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            lastUpdateLabel.setText("鏈€鍚庢洿鏂? " + time);
        }
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        // 浣跨敤绯荤粺鎻愮ず妗嗘樉绀烘秷鎭?        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("鎻愮ず");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 瀹炵幇DataUpdateListener鎺ュ彛 - 澶勭悊鏁版嵁鏇存柊閫氱煡
     */
    @Override
    public void onDataUpdated(String dataType, String operation) {
        logger.info("缁熻鐣岄潰鏀跺埌鏁版嵁鏇存柊閫氱煡: {} - {}", dataType, operation);

        // 鍦↗avaFX绾跨▼涓埛鏂版暟鎹?        javafx.application.Platform.runLater(() -> {
            try {
                refreshStatistics();
                logger.info("缁熻鏁版嵁宸茶嚜鍔ㄥ埛鏂?);
            } catch (Exception e) {
                logger.error("鑷姩鍒锋柊缁熻鏁版嵁澶辫触", e);
            }
        });
    }

    /**
     * 鏄剧ず閿欒娑堟伅
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("閿欒");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

========================================
StatisticsData.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

/**
 * 缁熻鏁版嵁绫?
 */
public class StatisticsData {
    private String category;
    private String name;
    private int count;
    private double percentage;
    private String status;

    public StatisticsData(String category, String name, int count, double percentage, String status) {
        this.category = category;
        this.name = name;
        this.count = count;
        this.percentage = percentage;
        this.status = status;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

========================================
StudentDashboardController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.dorm.controller.PaymentController;
import org.dorm.controller.StudentController;
import org.dorm.controller.ViolationController;
import org.dorm.controller.RepairApplicationController;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.Payment;
import org.dorm.model.entity.Violation;
import org.dorm.model.entity.RepairApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * 瀛︾敓涓汉涓績鎺у埗鍣?
 * 鏄剧ず瀛︾敓鐨勭即璐规儏鍐点€佽繚绾儏鍐点€佺淮淇敵璇枫€佷釜浜轰俊鎭瓑
 */
public class StudentDashboardController {
    private static final Logger logger = LoggerFactory.getLogger(StudentDashboardController.class);

    // 缂磋垂淇℃伅
    @FXML private Label pendingPaymentLabel;
    @FXML private Label currentMonthPaymentLabel;
    @FXML private Label totalPaymentLabel;

    // 杩濈邯淇℃伅
    @FXML private Label violationCountLabel;
    @FXML private Label latestViolationLabel;
    @FXML private Label violationStatusLabel;

    // 缁翠慨淇℃伅
    @FXML private Label pendingRepairLabel;
    @FXML private Label inProgressRepairLabel;
    @FXML private Label completedRepairLabel;

    // 涓汉淇℃伅
    @FXML private Label studentIdLabel;
    @FXML private Label nameLabel;
    @FXML private Label collegeLabel;
    @FXML private Label classLabel;
    @FXML private Label genderLabel;
    @FXML private Label bedIdLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;

    private String currentStudentId;
    private StudentController studentController;
    private PaymentController paymentController;
    private ViolationController violationController;
    private RepairApplicationController repairController;

    @FXML
    private void initialize() {
        logger.info("瀛︾敓涓汉涓績鍒濆鍖?);

        // 鍒濆鍖栨帶鍒跺櫒
        this.studentController = new StudentController();
        this.paymentController = new PaymentController();
        this.violationController = new ViolationController();
        this.repairController = new RepairApplicationController();

        // 鏄剧ず榛樿鍊?
        showDefaultValues();

        // 濡傛灉鏈夊鐢烮D锛屽姞杞芥暟鎹?
        if (currentStudentId != null) {
            loadStudentData();
        }
    }

    /**
     * 鏄剧ず榛樿鍊?
     */
    private void showDefaultValues() {
        // 缂磋垂淇℃伅
        pendingPaymentLabel.setText("楼0.00");
        currentMonthPaymentLabel.setText("楼0.00");
        totalPaymentLabel.setText("楼0.00");

        // 杩濈邯淇℃伅
        violationCountLabel.setText("0娆?);
        latestViolationLabel.setText("鏃?);
        violationStatusLabel.setText("鑹ソ");

        // 缁翠慨淇℃伅
        pendingRepairLabel.setText("0涓?);
        inProgressRepairLabel.setText("0涓?);
        completedRepairLabel.setText("0涓?);

        // 涓汉淇℃伅
        studentIdLabel.setText("鏈櫥褰?);
        nameLabel.setText("鏈櫥褰?);
        collegeLabel.setText("鏈缃?);
        classLabel.setText("鏈缃?);
        genderLabel.setText("鏈缃?);
        bedIdLabel.setText("鏈垎閰?);
        emailLabel.setText("鏈～鍐?);
        phoneLabel.setText("鏈～鍐?);
    }

    /**
     * 璁剧疆褰撳墠瀛︾敓ID
     */
    public void setCurrentStudentId(String studentId) {
        this.currentStudentId = studentId;
        logger.info("璁剧疆瀛︾敓ID: {}", studentId);

        // 纭繚鎺у埗鍣ㄥ凡鍒濆鍖?
        if (studentController == null) {
            this.studentController = new StudentController();
            this.paymentController = new PaymentController();
            this.violationController = new ViolationController();
            this.repairController = new RepairApplicationController();
        }

        // 鍔犺浇瀛︾敓鏁版嵁
        loadStudentData();
    }

    /**
     * 鍔犺浇瀛︾敓鏁版嵁
     */
    private void loadStudentData() {
        try {
            logger.info("鍔犺浇瀛︾敓鏁版嵁锛歿}", currentStudentId);

            // 鍔犺浇涓汉淇℃伅
            loadStudentInfo();

            // 鍔犺浇缂磋垂淇℃伅
            loadPaymentInfo();

            // 鍔犺浇杩濈邯淇℃伅
            loadViolationInfo();

            // 鍔犺浇缁翠慨淇℃伅
            loadRepairInfo();

            logger.info("瀛︾敓鏁版嵁鍔犺浇瀹屾垚锛歿}", currentStudentId);

        } catch (Exception e) {
            logger.error("鍔犺浇瀛︾敓鏁版嵁澶辫触锛歿}", currentStudentId, e);
        }
    }

    /**
     * 鍔犺浇涓汉淇℃伅
     */
    private void loadStudentInfo() {
        try {
            Student student = studentController.getStudentById(currentStudentId);
            if (student != null) {
                studentIdLabel.setText(student.getStudentId());
                nameLabel.setText(student.getName());
                collegeLabel.setText(student.getCollegeName());
                classLabel.setText(student.getClassName());
                genderLabel.setText("M".equals(student.getGender()) ? "鐢? : "F".equals(student.getGender()) ? "濂? : student.getGender());
                bedIdLabel.setText(student.getBedId() != null ? student.getBedId() : "鏈垎閰?);
                emailLabel.setText(student.getEmail() != null ? student.getEmail() : "鏈～鍐?);
                phoneLabel.setText(student.getPhone() != null ? student.getPhone() : "鏈～鍐?);
            }
        } catch (Exception e) {
            logger.error("鍔犺浇瀛︾敓淇℃伅澶辫触", e);
        }
    }

    /**
     * 鍔犺浇缂磋垂淇℃伅
     */
    private void loadPaymentInfo() {
        try {
            // 鑾峰彇鎵€鏈夌即璐硅褰?
            List<Payment> payments = paymentController.getPaymentsByStudentId(currentStudentId);

            BigDecimal pendingAmount = BigDecimal.ZERO;
            BigDecimal currentMonthAmount = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;

            LocalDate now = LocalDate.now();
            for (Payment payment : payments) {
                BigDecimal amount = payment.getAmount();
                if (amount != null) {
                    totalAmount = totalAmount.add(amount);

                    // 璁＄畻鏈湀缂磋垂
                    if (payment.getPaymentDate() != null) {
                        LocalDate paymentDate = payment.getPaymentDate().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate();
                        if (paymentDate.getYear() == now.getYear() && paymentDate.getMonth() == now.getMonth()) {
                            currentMonthAmount = currentMonthAmount.add(amount);
                        }
                    }

                    // 璁＄畻寰呯即璐癸紙鍋囪鏈即鐘舵€佷负寰呯即璐癸級
                    if ("鏈即".equals(payment.getStatus())) {
                        pendingAmount = pendingAmount.add(amount);
                    }
                }
            }

            pendingPaymentLabel.setText("楼" + pendingAmount.toString());
            currentMonthPaymentLabel.setText("楼" + currentMonthAmount.toString());
            totalPaymentLabel.setText("楼" + totalAmount.toString());

        } catch (Exception e) {
            logger.error("鍔犺浇缂磋垂淇℃伅澶辫触", e);
            pendingPaymentLabel.setText("楼0.00");
            currentMonthPaymentLabel.setText("楼0.00");
            totalPaymentLabel.setText("楼0.00");
        }
    }

    /**
     * 鍔犺浇杩濈邯淇℃伅
     */
    private void loadViolationInfo() {
        try {
            List<Violation> violations = violationController.getViolationsByStudentId(currentStudentId);

            violationCountLabel.setText(violations.size() + "娆?);

            if (!violations.isEmpty()) {
                // 鎵惧埌鏈€杩戠殑杩濈邯璁板綍
                Violation latest = violations.stream()
                    .filter(v -> v.getViolationDate() != null)
                    .max((v1, v2) -> v1.getViolationDate().compareTo(v2.getViolationDate()))
                    .orElse(violations.get(0));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                latestViolationLabel.setText(sdf.format(latest.getViolationDate()));

                // 鏍规嵁杩濈邯娆℃暟璁剧疆鐘舵€?
                if (violations.size() >= 3) {
                    violationStatusLabel.setText("涓ラ噸");
                    violationStatusLabel.getStyleClass().add("danger");
                } else if (violations.size() >= 1) {
                    violationStatusLabel.setText("涓€鑸?);
                    violationStatusLabel.getStyleClass().add("warning");
                } else {
                    violationStatusLabel.setText("鑹ソ");
                    violationStatusLabel.getStyleClass().add("good");
                }
            } else {
                latestViolationLabel.setText("鏃?);
                violationStatusLabel.setText("鑹ソ");
                violationStatusLabel.getStyleClass().add("good");
            }

        } catch (Exception e) {
            logger.error("鍔犺浇杩濈邯淇℃伅澶辫触", e);
            violationCountLabel.setText("0娆?);
            latestViolationLabel.setText("鏃?);
            violationStatusLabel.setText("鑹ソ");
        }
    }

    /**
     * 鍔犺浇缁翠慨淇℃伅
     */
    private void loadRepairInfo() {
        try {
            List<RepairApplication> repairs = repairController.getStudentApplications(currentStudentId);

            int pendingCount = 0;
            int inProgressCount = 0;
            int completedCount = 0;

            for (RepairApplication repair : repairs) {
                switch (repair.getStatus()) {
                    case "寰呭鐞?:
                        pendingCount++;
                        break;
                    case "杩涜涓?:
                        inProgressCount++;
                        break;
                    case "宸插畬鎴?:
                        completedCount++;
                        break;
                }
            }

            pendingRepairLabel.setText(pendingCount + "涓?);
            inProgressRepairLabel.setText(inProgressCount + "涓?);
            completedRepairLabel.setText(completedCount + "涓?);

        } catch (Exception e) {
            logger.error("鍔犺浇缁翠慨淇℃伅澶辫触", e);
            pendingRepairLabel.setText("0涓?);
            inProgressRepairLabel.setText("0涓?);
            completedRepairLabel.setText("0涓?);
        }
    }

    // 浜嬩欢澶勭悊鏂规硶
    @FXML
    private void showPaymentDetails(ActionEvent event) {
        // 杩欓噷鍙互瀵艰埅鍒扮即璐硅鎯呴〉闈?
        logger.info("鏌ョ湅缂磋垂璇︽儏");
    }

    @FXML
    private void showViolationDetails(ActionEvent event) {
        // 杩欓噷鍙互瀵艰埅鍒拌繚绾鎯呴〉闈?
        logger.info("鏌ョ湅杩濈邯璇︽儏");
    }

    @FXML
    private void showRepairDetails(ActionEvent event) {
        // 杩欓噷鍙互瀵艰埅鍒扮淮淇鎯呴〉闈?
        logger.info("鏌ョ湅缁翠慨璇︽儏");
    }

    @FXML
    private void showEditProfile(ActionEvent event) {
        // 杩欓噷鍙互瀵艰埅鍒颁慨鏀逛釜浜轰俊鎭〉闈?
        logger.info("淇敼涓汉淇℃伅");
    }
}

========================================
StudentInfoController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.dorm.controller.StudentController;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 瀛︾敓涓汉淇℃伅鐣岄潰鎺у埗鍣?
 */
public class StudentInfoController {
    private static final Logger logger = LoggerFactory.getLogger(StudentInfoController.class);

    @FXML private Label studentIdLabel;
    @FXML private Label nameLabel;
    @FXML private Label genderLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label collegeLabel;
    @FXML private Label classLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label buildingLabel;
    @FXML private Label roomLabel;
    @FXML private Label bedLabel;
    @FXML private Label statusLabel;
    @FXML private Button editButton;
    @FXML private Button refreshButton;
    @FXML private Button backButton;

    private StudentController studentController = new StudentController();
    private User currentUser;
    private Student currentStudent;

    @FXML
    private void initialize() {
        logger.info("瀛︾敓涓汉淇℃伅鐣岄潰鍒濆鍖栧畬鎴?);
    }

    /**
     * 璁剧疆褰撳墠鐢ㄦ埛
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        loadStudentInfo();
    }

    /**
     * 鍔犺浇瀛︾敓淇℃伅
     */
    private void loadStudentInfo() {
        if (currentUser == null) {
            showError("鐢ㄦ埛淇℃伅鏈缃?);
            return;
        }

        try {
            // 鑾峰彇瀛︾敓淇℃伅
            currentStudent = studentController.getStudentById(currentUser.getUserId());

            if (currentStudent != null) {
                // 鏄剧ず瀛︾敓鍩烘湰淇℃伅
                studentIdLabel.setText(currentStudent.getStudentId());
                nameLabel.setText(currentStudent.getName() != null ? currentStudent.getName() : "-");
                genderLabel.setText(currentStudent.getGender() != null ? currentStudent.getGender() : "-");
                birthdayLabel.setText(currentStudent.getBirthday() != null ?
                    new java.text.SimpleDateFormat("yyyy-MM-dd").format(currentStudent.getBirthday()) : "-");
                collegeLabel.setText(currentStudent.getCollegeName() != null ? currentStudent.getCollegeName() : "-");
                classLabel.setText(currentStudent.getClassName() != null ? currentStudent.getClassName() : "-");
                emailLabel.setText(currentStudent.getEmail() != null ? currentStudent.getEmail() : "-");
                phoneLabel.setText(currentStudent.getPhone() != null ? currentStudent.getPhone() : "-");

                // 鏄剧ず浣忓淇℃伅
                if (currentStudent.getBedId() != null && !currentStudent.getBedId().isEmpty()) {
                    // 瑙ｆ瀽搴婁綅ID锛屾牸寮忛€氬父涓?"鏂囩€?-101-01"
                    String bedId = currentStudent.getBedId();
                    String[] parts = bedId.split("-");
                    if (parts.length >= 3) {
                        // 閲嶆柊缁勫悎瀹胯垗妤煎悕绉?
                        String buildingName = parts[0]; // 鏂囩€?
                        // 鏍规嵁鍓嶇紑纭畾瀹屾暣鐨勬ゼ鍚?
                        if (buildingName.startsWith("鏂囩€?)) {
                            buildingLabel.setText("鏂囩€涜嫅");
                        } else if (buildingName.startsWith("鏂囬煬")) {
                            buildingLabel.setText("鏂囬煬鑻?);
                        } else if (buildingName.startsWith("鏂囨緶")) {
                            buildingLabel.setText("鏂囨緶鑻?);
                        } else if (buildingName.startsWith("鎬′竵")) {
                            buildingLabel.setText("鎬′竵鑻?);
                        } else {
                            buildingLabel.setText(buildingName);
                        }

                        roomLabel.setText(parts[1]);     // 101
                        bedLabel.setText(parts[2]);      // 01
                    } else {
                        buildingLabel.setText("-");
                        roomLabel.setText("-");
                        bedLabel.setText(bedId);
                    }
                } else {
                    buildingLabel.setText("-");
                    roomLabel.setText("-");
                    bedLabel.setText("-");
                }

                statusLabel.setText("淇℃伅鍔犺浇瀹屾垚");
                logger.info("瀛︾敓淇℃伅鍔犺浇鎴愬姛锛歿}", currentStudent.getStudentId());
            } else {
                showError("鏈壘鍒板鐢熶俊鎭紝璇疯仈绯荤鐞嗗憳");
                clearAllFields();
            }

        } catch (Exception e) {
            logger.error("鍔犺浇瀛︾敓淇℃伅澶辫触", e);
            showError("鍔犺浇淇℃伅澶辫触锛? + e.getMessage());
            clearAllFields();
        }
    }

    /**
     * 娓呯┖鎵€鏈夊瓧娈?
     */
    private void clearAllFields() {
        studentIdLabel.setText("-");
        nameLabel.setText("-");
        genderLabel.setText("-");
        birthdayLabel.setText("-");
        collegeLabel.setText("-");
        classLabel.setText("-");
        emailLabel.setText("-");
        phoneLabel.setText("-");
        buildingLabel.setText("-");
        roomLabel.setText("-");
        bedLabel.setText("-");
    }

    /**
     * 缂栬緫淇℃伅
     */
    @FXML
    private void handleEdit(ActionEvent event) {
        if (currentStudent == null) {
            showError("娌℃湁鍙紪杈戠殑淇℃伅");
            return;
        }

        try {
            // 鍔犺浇瀛︾敓绠＄悊鐣岄潰杩涜缂栬緫
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student_management.fxml"));
            Parent root = loader.load();

            StudentManagementController controller = loader.getController();
            controller.setStudentMode(currentUser.getUserId());

            Stage stage = new Stage();
            stage.setTitle("缂栬緫涓汉淇℃伅");
            stage.setScene(new Scene(root, 1200, 700));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // 缂栬緫瀹屾垚鍚庡埛鏂颁俊鎭?
            loadStudentInfo();

        } catch (IOException e) {
            logger.error("鎵撳紑缂栬緫鐣岄潰澶辫触", e);
            showError("鎵撳紑缂栬緫鐣岄潰澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 鍒锋柊淇℃伅
     */
    @FXML
    private void handleRefresh(ActionEvent event) {
        loadStudentInfo();
        statusLabel.setText("淇℃伅宸插埛鏂?);
    }

    /**
     * 杩斿洖涓婁竴绾?
     */
    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    /**
     * 鏄剧ず閿欒淇℃伅
     */
    private void showError(String message) {
        statusLabel.setText("閿欒锛? + message);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("閿欒");
        alert.setHeaderText("鎿嶄綔澶辫触");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

========================================
StudentMainController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 瀛︾敓涓荤晫闈㈡帶鍒跺櫒
 * 涓撲负瀛︾敓鐢ㄦ埛璁捐鐨勭畝鍖栫晫闈紝鍙樉绀哄鐢熺浉鍏崇殑鍔熻兘
 */
public class StudentMainController {
    private static final Logger logger = LoggerFactory.getLogger(StudentMainController.class);

    @FXML private Label titleLabel;
    @FXML private Label userInfoLabel;
    @FXML private Label welcomeLabel;
    @FXML private Button logoutButton;
    @FXML private Button myInfoButton;
    @FXML private Button submitRepairButton;
    @FXML private Button myRepairButton;
    @FXML private Button myPaymentButton;
    @FXML private Button myViolationButton;
    @FXML private Button changePasswordButton;
    @FXML private VBox contentArea;

    private User currentUser;

    @FXML
    private void initialize() {
        logger.info("瀛︾敓涓荤晫闈㈠垵濮嬪寲瀹屾垚");
    }

    /**
     * 璁剧疆褰撳墠鐢ㄦ埛
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;

        // 鏇存柊鐢ㄦ埛淇℃伅鏄剧ず
        String userTypeText = "admin".equals(user.getUserType()) ? "绠＄悊鍛? : "student".equals(user.getUserType()) ? "瀛︾敓" : user.getUserType();
        userInfoLabel.setText("褰撳墠鐢ㄦ埛锛? + currentUser.getUserId() + " (" + userTypeText + ")");

        // 鏇存柊娆㈣繋淇℃伅
        welcomeLabel.setText("娆㈣繋鎮紝" + currentUser.getUserId() + "锛?);

        logger.info("璁剧疆褰撳墠瀛︾敓鐢ㄦ埛锛歿}", currentUser.getUserId());
    }

    /**
     * 鍔犺浇鍐呭鍒颁富鐣岄潰
     */
    private void loadContent(String fxmlPath, String title) {
        try {
            logger.info("姝ｅ湪鍔犺浇鐣岄潰锛歿} - {}", title, fxmlPath);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();

            // 濡傛灉鎺у埗鍣ㄦ湁setCurrentUser鏂规硶锛屽垯璁剧疆鐢ㄦ埛淇℃伅
            if (loader.getController() instanceof StudentInfoController) {
                ((StudentInfoController) loader.getController()).setCurrentUser(currentUser);
            } else if (loader.getController() instanceof SubmitRepairController) {
                ((SubmitRepairController) loader.getController()).setCurrentUser(currentUser);
            } else if (loader.getController() instanceof StudentRepairController) {
                ((StudentRepairController) loader.getController()).setCurrentUser(currentUser);
            } else if (loader.getController() instanceof ChangePasswordController) {
                ((ChangePasswordController) loader.getController()).setCurrentUser(currentUser);
            } else if (loader.getController() instanceof PaymentManagementController) {
                // 涓虹即璐圭鐞嗚缃鐢熸ā寮忥紝鍙樉绀哄綋鍓嶅鐢熺殑缂磋垂璁板綍
                ((PaymentManagementController) loader.getController()).setStudentMode(currentUser.getUserId());
            } else if (loader.getController() instanceof ViolationManagementController) {
                // 涓鸿繚绾鐞嗚缃鐢熸ā寮忥紝鍙樉绀哄綋鍓嶅鐢熺殑杩濈邯璁板綍
                ((ViolationManagementController) loader.getController()).setStudentMode(currentUser.getUserId());
            }

            // 娓呯┖鍐呭鍖哄煙骞舵坊鍔犳柊鍐呭
            contentArea.getChildren().clear();
            contentArea.getChildren().add(content);

            logger.info("鐣岄潰鍔犺浇鎴愬姛锛歿}", title);
        } catch (IOException e) {
            logger.error("鍔犺浇鐣岄潰澶辫触锛歿}", fxmlPath, e);
        }
    }

    /**
     * 鏄剧ず鎴戠殑淇℃伅
     */
    @FXML
    private void showStudentMyInfo(ActionEvent event) {
        logger.info("鏄剧ず鎴戠殑淇℃伅");
        loadContent("/fxml/student_info.fxml", "鎴戠殑淇℃伅");
    }

    /**
     * 鏄剧ず鎻愪氦缁翠慨鐢宠鐣岄潰
     */
    @FXML
    private void showStudentSubmitRepair(ActionEvent event) {
        logger.info("鏄剧ず鎻愪氦缁翠慨鐢宠");
        loadContent("/fxml/submit_repair.fxml", "鎻愪氦缁翠慨鐢宠");
    }

    /**
     * 鏄剧ず鎴戠殑缁翠慨鐢宠
     */
    @FXML
    private void showStudentMyRepair(ActionEvent event) {
        logger.info("鏄剧ず鎴戠殑缁翠慨鐢宠");
        loadContent("/fxml/student_repair.fxml", "鎴戠殑缁翠慨鐢宠");
    }

    /**
     * 鏄剧ず鎴戠殑缂磋垂璁板綍
     */
    @FXML
    private void showStudentMyPayment(ActionEvent event) {
        logger.info("鏄剧ず鎴戠殑缂磋垂璁板綍");
        loadContent("/fxml/payment_management.fxml", "鎴戠殑缂磋垂璁板綍");
    }

    /**
     * 鏄剧ず鎴戠殑杩濈邯璁板綍
     */
    @FXML
    private void showStudentMyViolation(ActionEvent event) {
        logger.info("鏄剧ず鎴戠殑杩濈邯璁板綍");
        loadContent("/fxml/violation_management.fxml", "鎴戠殑杩濈邯璁板綍");
    }

    /**
     * 鏄剧ず淇敼瀵嗙爜鐣岄潰
     */
    @FXML
    private void showStudentChangePassword(ActionEvent event) {
        logger.info("鏄剧ず淇敼瀵嗙爜");
        loadContent("/fxml/change_password.fxml", "淇敼瀵嗙爜");
    }

    /**
     * 澶勭悊閫€鍑虹櫥褰?
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        logger.info("閫€鍑虹櫥褰?);
        try {
            // 鍔犺浇鐧诲綍鐣岄潰
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            // 鑾峰彇褰撳墠鑸炲彴
            Stage stage = (Stage) logoutButton.getScene().getWindow();

            // 璁剧疆鏂板満鏅?
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.setTitle("瀹胯垗绠＄悊绯荤粺 - 鐧诲綍");
            stage.centerOnScreen();

            logger.info("鎴愬姛閫€鍑哄苟杩斿洖鐧诲綍鐣岄潰");
        } catch (IOException e) {
            logger.error("鍔犺浇鐧诲綍鐣岄潰澶辫触", e);
        }
    }
}

========================================
StudentManagementController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.dorm.controller.StudentController;
import org.dorm.model.entity.Student;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 瀛︾敓绠＄悊鐣岄潰鎺у埗鍣?
 */
public class StudentManagementController {
    private static final Logger logger = LoggerFactory.getLogger(StudentManagementController.class);

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> searchTypeCombo;
    @FXML
    private Label statusLabel;
    @FXML
    private Label selectedLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label lastUpdateLabel;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> studentIdColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> collegeColumn;
    @FXML
    private TableColumn<Student, String> classColumn;
    @FXML
    private TableColumn<Student, String> genderColumn;
    @FXML
    private TableColumn<Student, String> birthdayColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> phoneColumn;
    @FXML
    private TableColumn<Student, String> bedIdColumn;

    private ObservableList<Student> studentData = FXCollections.observableArrayList();
    private boolean isStudentMode = false;
    private String currentStudentId = null;

    /**
     * 璁剧疆涓哄鐢熸ā寮忥紝鍙樉绀烘寚瀹氬鐢熺殑淇℃伅
     */
    public void setStudentMode(String studentId) {
        this.isStudentMode = true;
        this.currentStudentId = studentId;

        // 鍦ㄥ鐢熸ā寮忎笅闅愯棌绠＄悊鍔熻兘锛堝欢杩熷埌initialize涔嬪悗鎵ц锛?
        Platform.runLater(() -> {
            if (addButton != null) addButton.setVisible(false);
            if (editButton != null) editButton.setVisible(false);
            if (deleteButton != null) deleteButton.setVisible(false);
        });

        // 閲嶆柊鍔犺浇鏁版嵁
        loadStudents();
    }

    @FXML
    private void initialize() {
        logger.info("瀛︾敓绠＄悊鐣岄潰鍒濆鍖栧紑濮?);

        // 鍒濆鍖栬〃鏍煎垪
        setupTableColumns();

        // 鍒濆鍖栨悳绱㈢被鍨?
        if (searchTypeCombo != null) {
            searchTypeCombo.setItems(FXCollections.observableArrayList("鍏ㄩ儴", "瀛﹀彿", "濮撳悕", "瀛﹂櫌"));
            searchTypeCombo.setValue("鍏ㄩ儴");
        }

        // 鍒濆鍖栭€変腑椤硅鏁?
        if (selectedLabel != null) {
            selectedLabel.setText("宸查€夋嫨 0 椤?);
        }

        // 鍒濆鍖栬〃鏍奸€夋嫨鐩戝惉鍣?
        if (studentTable != null) {
            studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                updateSelectedCount();
            });
        }

        // 鍔犺浇鍒濆鏁版嵁
        loadStudents();

        logger.info("瀛︾敓绠＄悊鐣岄潰鍒濆鍖栧畬鎴?);
    }

    /**
     * 鍒濆鍖栬〃鏍煎垪
     */
    private void setupTableColumns() {
        if (studentIdColumn != null) {
            studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        }
        if (nameColumn != null) {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        }
        if (collegeColumn != null) {
            collegeColumn.setCellValueFactory(new PropertyValueFactory<>("collegeName"));
        }
        if (classColumn != null) {
            classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        }
        if (genderColumn != null) {
            genderColumn.setCellValueFactory(cellData -> {
                String gender = cellData.getValue().getGender();
                String displayGender = "M".equals(gender) ? "鐢? : "F".equals(gender) ? "濂? : gender;
                return new javafx.beans.property.SimpleStringProperty(displayGender);
            });
        }
        if (birthdayColumn != null) {
            birthdayColumn.setCellValueFactory(cellData -> {
                Student student = cellData.getValue();
                java.util.Date birthday = student.getBirthday();
                if (birthday != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return new javafx.beans.property.SimpleStringProperty(sdf.format(birthday));
                } else {
                    return new javafx.beans.property.SimpleStringProperty("");
                }
            });
        }
        if (emailColumn != null) {
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        }
        if (phoneColumn != null) {
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        }
        if (bedIdColumn != null) {
            bedIdColumn.setCellValueFactory(new PropertyValueFactory<>("bedId"));
        }

        // 璁剧疆琛ㄦ牸鏁版嵁
        if (studentTable != null) {
            studentTable.setItems(studentData);
        }
    }

    /**
     * 鍔犺浇瀛︾敓鏁版嵁
     */
    private void loadStudents() {
        try {
            // #region agent log
            logDebugEvent("StudentManagementController.java:150", "寮€濮嬪垱寤篠tudentController瀹炰緥", java.util.Map.of(), "C");
            // #endregion

            // 鎸夐渶鍒涘缓Controller瀹炰緥锛岄伩鍏嶅湪搴旂敤鍚姩鏃跺氨杩炴帴鏁版嵁搴?
            StudentController studentController = new StudentController();

            List<Student> students;

            if (isStudentMode && currentStudentId != null) {
                // 瀛︾敓妯″紡锛氬彧鍔犺浇褰撳墠瀛︾敓鐨勪俊鎭?
                Student student = studentController.getStudentById(currentStudentId);
                students = student != null ? List.of(student) : List.of();
            } else {
                // 绠＄悊鍛樻ā寮忥細鍔犺浇鎵€鏈夊鐢熶俊鎭?
                students = studentController.getAllStudents();
            }

            studentData.clear();
            studentData.addAll(students);

            String statusText = isStudentMode ? "涓汉淇℃伅" : "鍏?" + students.size() + " 鏉¤褰?;
            if (statusLabel != null) {
                statusLabel.setText(statusText);
            }

            updateLastUpdateTime();

            // 閲嶇疆閫変腑璁℃暟
            if (selectedLabel != null) {
                selectedLabel.setText("宸查€夋嫨 0 椤?);
            }

            showMessage("鏁版嵁鍔犺浇瀹屾垚", "success");

            logger.info("瀛︾敓鏁版嵁鍔犺浇鎴愬姛锛歿}鏉¤褰?, students.size());

        } catch (Exception e) {
            // #region agent log
            logDebugEvent("StudentManagementController.java:182", "鍔犺浇瀛︾敓鏁版嵁澶辫触", java.util.Map.of("error", e.getMessage(), "errorType", e.getClass().getSimpleName()), "C");
            // #endregion

            logger.error("鍔犺浇瀛︾敓鏁版嵁澶辫触", e);
            showMessage("鍔犺浇鏁版嵁澶辫触锛? + e.getMessage(), "error");
        }
    }

    /**
     * 鏇存柊鏈€鍚庢洿鏂版椂闂?
     */
    private void updateLastUpdateTime() {
        if (lastUpdateLabel != null) {
            lastUpdateLabel.setText("鏈€鍚庢洿鏂? " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
    }

    /**
     * 鏇存柊閫変腑椤硅鏁?
     */
    private void updateSelectedCount() {
        if (studentTable != null && selectedLabel != null) {
            int selectedCount = studentTable.getSelectionModel().getSelectedItems().size();
            selectedLabel.setText("宸查€夋嫨 " + selectedCount + " 椤?);
        }
    }


    /**
     * 鏄剧ず娣诲姞瀛︾敓瀵硅瘽妗?
     */
    @FXML
    private void showAddDialog(ActionEvent event) {
        // 鍒涘缓瀵硅瘽妗?
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("娣诲姞瀛︾敓");
        dialog.setHeaderText("璇疯緭鍏ユ柊瀛︾敓鐨勪俊鎭?);

        // 璁剧疆鎸夐挳
        ButtonType saveButtonType = new ButtonType("淇濆瓨", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentIdField = new TextField();
        studentIdField.setPromptText("瀛﹀彿锛堝锛?023001锛?);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("鐧诲綍瀵嗙爜");
        TextField nameField = new TextField();
        nameField.setPromptText("瀛︾敓濮撳悕");
        TextField collegeField = new TextField();
        collegeField.setPromptText("瀛﹂櫌鍚嶇О锛堝锛氳绠楁満瀛﹂櫌锛?);
        TextField classField = new TextField();
        classField.setPromptText("鐝骇锛堝锛氳绠楁満2101锛?);
        ComboBox<String> genderCombo = new ComboBox<>();
        genderCombo.setItems(FXCollections.observableArrayList("鐢?, "濂?));
        genderCombo.setValue("鐢?);
        DatePicker birthdayPicker = new DatePicker();
        birthdayPicker.setPromptText("鐢熸棩锛堝彲閫夛級");
        TextField emailField = new TextField();
        emailField.setPromptText("鐢靛瓙閭锛堝彲閫夛級");
        TextField phoneField = new TextField();
        phoneField.setPromptText("鑱旂郴鐢佃瘽锛堝彲閫夛級");
        TextField bedIdField = new TextField();
        bedIdField.setPromptText("搴婁綅鍙凤紙濡傦細A101-001锛屽彲閫夛級");

        grid.add(new Label("瀛﹀彿*:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("瀵嗙爜*:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("濮撳悕*:"), 0, 2);
        grid.add(nameField, 1, 2);
        grid.add(new Label("瀛﹂櫌*:"), 0, 3);
        grid.add(collegeField, 1, 3);
        grid.add(new Label("鐝骇*:"), 0, 4);
        grid.add(classField, 1, 4);
        grid.add(new Label("鎬у埆*:"), 0, 5);
        grid.add(genderCombo, 1, 5);
        grid.add(new Label("鐢熸棩:"), 0, 6);
        grid.add(birthdayPicker, 1, 6);
        grid.add(new Label("閭:"), 0, 7);
        grid.add(emailField, 1, 7);
        grid.add(new Label("鐢佃瘽:"), 0, 8);
        grid.add(phoneField, 1, 8);
        grid.add(new Label("搴婁綅鍙?"), 0, 9);
        grid.add(bedIdField, 1, 9);

        dialog.getDialogPane().setContent(grid);

        // 楠岃瘉杈撳叆
        dialog.getDialogPane().lookupButton(saveButtonType).setDisable(true);
        java.util.function.Consumer<String> validateInput = (s) -> {
            String studentId = studentIdField.getText().trim();
            boolean disable = studentId.isEmpty() ||
                             passwordField.getText().trim().isEmpty() ||
                             nameField.getText().trim().isEmpty() ||
                             collegeField.getText().trim().isEmpty() ||
                             classField.getText().trim().isEmpty() ||
                             genderCombo.getValue() == null ||
                             !isValidStudentId(studentId);
            dialog.getDialogPane().lookupButton(saveButtonType).setDisable(disable);
        };

        studentIdField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        passwordField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        nameField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        collegeField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        classField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        genderCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateInput.accept(""));

        // 澶勭悊缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Student student = new Student();
                student.setStudentId(studentIdField.getText().trim());
                student.setPassword(passwordField.getText().trim());
                student.setName(nameField.getText().trim());
                student.setCollegeName(collegeField.getText().trim());
                student.setClassName(classField.getText().trim());
                student.setGender(genderCombo.getValue());
                if (birthdayPicker.getValue() != null) {
                    student.setBirthday(java.sql.Date.valueOf(birthdayPicker.getValue()));
                }
                student.setEmail(emailField.getText().trim().isEmpty() ? null : emailField.getText().trim());
                student.setPhone(phoneField.getText().trim().isEmpty() ? null : phoneField.getText().trim());
                String bedId = bedIdField.getText().trim();
                if (!bedId.isEmpty() && isBedOccupied(bedId)) {
                    throw new IllegalArgumentException("璇ュ簥浣嶅凡琚崰鐢紝璇烽€夋嫨鍏朵粬搴婁綅锛?);
                }
                student.setBedId(bedId.isEmpty() ? null : bedId);
                return student;
            }
            return null;
        });

        java.util.Optional<Student> result = dialog.showAndWait();
        result.ifPresent(student -> {
            try {
                StudentController studentController = new StudentController();
                boolean success = studentController.addStudent(student);
                if (success) {
                    showMessage("娣诲姞瀛︾敓鎴愬姛锛?, "success");
                    loadStudents(); // 鍒锋柊鏁版嵁
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyStudentDataChanged("add");
                    logger.info("娣诲姞瀛︾敓鎴愬姛锛歿}", student.getStudentId());
                } else {
                    showMessage("娣诲姞瀛︾敓澶辫触锛屽彲鑳藉鍙峰凡瀛樺湪", "error");
                }
            } catch (Exception e) {
                logger.error("娣诲姞瀛︾敓澶辫触", e);
                showMessage("娣诲姞瀛︾敓澶辫触锛? + e.getMessage(), "error");
            }
        });
    }

    /**
     * 鏄剧ず缂栬緫瀛︾敓瀵硅瘽妗?
     */
    @FXML
    private void showEditDialog(ActionEvent event) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showMessage("璇峰厛閫夋嫨瑕佺紪杈戠殑瀛︾敓锛?, "warning");
            return;
        }

        // 鍒涘缓瀵硅瘽妗?
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("缂栬緫瀛︾敓");
        dialog.setHeaderText("淇敼瀛︾敓淇℃伅");

        // 璁剧疆鎸夐挳
        ButtonType saveButtonType = new ButtonType("淇濆瓨", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentIdField = new TextField(selectedStudent.getStudentId());
        studentIdField.setEditable(false); // 瀛﹀彿涓嶅厑璁镐慨鏀?
        studentIdField.setStyle("-fx-background-color: #f5f5f5;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("鐧诲綍瀵嗙爜锛堢暀绌鸿〃绀轰笉淇敼锛?);
        TextField nameField = new TextField(selectedStudent.getName());
        nameField.setPromptText("瀛︾敓濮撳悕");
        TextField collegeField = new TextField(selectedStudent.getCollegeName());
        collegeField.setPromptText("瀛﹂櫌鍚嶇О锛堝锛氳绠楁満瀛﹂櫌锛?);
        TextField classField = new TextField(selectedStudent.getClassName());
        classField.setPromptText("鐝骇锛堝锛氳绠楁満2101锛?);
        ComboBox<String> genderCombo = new ComboBox<>();
        genderCombo.setItems(FXCollections.observableArrayList("鐢?, "濂?));
        genderCombo.setValue(selectedStudent.getGender());
        DatePicker birthdayPicker = new DatePicker();
        if (selectedStudent.getBirthday() != null) {
            birthdayPicker.setValue(new java.sql.Date(selectedStudent.getBirthday().getTime()).toLocalDate());
        }
        birthdayPicker.setPromptText("鐢熸棩锛堝彲閫夛級");
        TextField emailField = new TextField(selectedStudent.getEmail() != null ? selectedStudent.getEmail() : "");
        emailField.setPromptText("鐢靛瓙閭锛堝彲閫夛級");
        TextField phoneField = new TextField(selectedStudent.getPhone() != null ? selectedStudent.getPhone() : "");
        phoneField.setPromptText("鑱旂郴鐢佃瘽锛堝彲閫夛級");
        TextField bedIdField = new TextField(selectedStudent.getBedId() != null ? selectedStudent.getBedId() : "");
        bedIdField.setPromptText("搴婁綅鍙凤紙濡傦細A101-001锛屽彲閫夛級");

        grid.add(new Label("瀛﹀彿*:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("瀵嗙爜:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("濮撳悕*:"), 0, 2);
        grid.add(nameField, 1, 2);
        grid.add(new Label("瀛﹂櫌*:"), 0, 3);
        grid.add(collegeField, 1, 3);
        grid.add(new Label("鐝骇*:"), 0, 4);
        grid.add(classField, 1, 4);
        grid.add(new Label("鎬у埆*:"), 0, 5);
        grid.add(genderCombo, 1, 5);
        grid.add(new Label("鐢熸棩:"), 0, 6);
        grid.add(birthdayPicker, 1, 6);
        grid.add(new Label("閭:"), 0, 7);
        grid.add(emailField, 1, 7);
        grid.add(new Label("鐢佃瘽:"), 0, 8);
        grid.add(phoneField, 1, 8);
        grid.add(new Label("搴婁綅鍙?"), 0, 9);
        grid.add(bedIdField, 1, 9);

        dialog.getDialogPane().setContent(grid);

        // 楠岃瘉杈撳叆
        dialog.getDialogPane().lookupButton(saveButtonType).setDisable(true);
        java.util.function.Consumer<String> validateInput = (s) -> {
            boolean disable = nameField.getText().trim().isEmpty() ||
                             collegeField.getText().trim().isEmpty() ||
                             classField.getText().trim().isEmpty() ||
                             genderCombo.getValue() == null;
            dialog.getDialogPane().lookupButton(saveButtonType).setDisable(disable);
        };

        nameField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        collegeField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        classField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        genderCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateInput.accept(""));

        // 澶勭悊缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Student student = new Student();
                student.setStudentId(selectedStudent.getStudentId()); // 瀛﹀彿淇濇寔涓嶅彉
                // 濡傛灉瀵嗙爜涓虹┖锛屼繚鎸佸師鏈夊瘑鐮?
                String newPassword = passwordField.getText().trim();
                if (!newPassword.isEmpty()) {
                    // 楠岃瘉鏂板瘑鐮侀暱搴?
                    if (newPassword.length() < 6) {
                        showMessage("瀵嗙爜闀垮害鑷冲皯6浣嶏紒", "warning");
                        return null;
                    }
                    student.setPassword(newPassword);
                } else {
                    // 淇濇寔鍘熸湁瀵嗙爜
                    student.setPassword(selectedStudent.getPassword());
                }
                student.setName(nameField.getText().trim());
                student.setCollegeName(collegeField.getText().trim());
                student.setClassName(classField.getText().trim());
                student.setGender(genderCombo.getValue());
                if (birthdayPicker.getValue() != null) {
                    student.setBirthday(java.sql.Date.valueOf(birthdayPicker.getValue()));
                }
                student.setEmail(emailField.getText().trim().isEmpty() ? null : emailField.getText().trim());
                student.setPhone(phoneField.getText().trim().isEmpty() ? null : phoneField.getText().trim());
                String bedId = bedIdField.getText().trim();
                if (!bedId.isEmpty()) {
                    // 楠岃瘉搴婁綅鏄惁宸茶鍗犵敤锛堟帓闄ゅ綋鍓嶅鐢熺殑搴婁綅锛?
                    if (isBedOccupied(bedId) && !bedId.equals(selectedStudent.getBedId())) {
                        showMessage("璇ュ簥浣嶅凡琚崰鐢紝璇烽€夋嫨鍏朵粬搴婁綅锛?, "warning");
                        return null;
                    }
                }
                student.setBedId(bedId.isEmpty() ? null : bedId);
                return student;
            }
            return null;
        });

        java.util.Optional<Student> result = dialog.showAndWait();
        result.ifPresent(student -> {
            try {
                StudentController studentController = new StudentController();
                boolean success = studentController.updateStudent(student);
                if (success) {
                    showMessage("缂栬緫瀛︾敓鎴愬姛锛?, "success");
                    loadStudents(); // 鍒锋柊鏁版嵁
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyStudentDataChanged("update");
                    logger.info("缂栬緫瀛︾敓鎴愬姛锛歿}", student.getStudentId());
                } else {
                    showMessage("缂栬緫瀛︾敓澶辫触", "error");
                }
            } catch (Exception e) {
                logger.error("缂栬緫瀛︾敓澶辫触", e);
                showMessage("缂栬緫瀛︾敓澶辫触锛? + e.getMessage(), "error");
            }
        });
    }

    /**
     * 鍒犻櫎瀛︾敓
     */
    @FXML
    private void deleteStudent(ActionEvent event) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showMessage("璇峰厛閫夋嫨瑕佸垹闄ょ殑瀛︾敓锛?, "warning");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("纭鍒犻櫎");
        alert.setHeaderText("纭畾瑕佸垹闄ゅ鐢熷悧锛?);
        alert.setContentText("瀛﹀彿: " + selectedStudent.getStudentId() + "\n濮撳悕: " + selectedStudent.getName());

        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                StudentController studentController = new StudentController();
                boolean success = studentController.deleteStudent(selectedStudent.getStudentId());
                if (success) {
                    showMessage("鍒犻櫎瀛︾敓鎴愬姛锛?, "success");
                    loadStudents(); // 鍒锋柊鏁版嵁
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyStudentDataChanged("delete");
                } else {
                    showMessage("鍒犻櫎瀛︾敓澶辫触", "error");
                }
            } catch (Exception e) {
                showMessage("鍒犻櫎瀛︾敓澶辫触锛? + e.getMessage(), "error");
            }
        }
    }

    /**
     * 鍒锋柊瀛︾敓鍒楄〃
     */
    @FXML
    private void refreshStudents(ActionEvent event) {
        loadStudents();
    }

    /**
     * 鎼滅储瀛︾敓
     */
    @FXML
    private void searchStudents(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            loadStudents(); // 濡傛灉鎼滅储涓虹┖锛屾樉绀烘墍鏈夎褰?
            return;
        }

        try {
            StudentController studentController = new StudentController();
            List<Student> allStudents = studentController.getAllStudents();
            List<Student> filteredStudents;

            switch (searchType) {
                case "瀛﹀彿":
                    filteredStudents = allStudents.stream()
                        .filter(s -> s.getStudentId().toLowerCase().contains(searchText))
                        .toList();
                    break;
                case "濮撳悕":
                    filteredStudents = allStudents.stream()
                        .filter(s -> s.getName().toLowerCase().contains(searchText))
                        .toList();
                    break;
                default:
                    // 鍏ㄩ儴鎼滅储
                    filteredStudents = allStudents.stream()
                        .filter(s -> s.getStudentId().toLowerCase().contains(searchText) ||
                                   s.getName().toLowerCase().contains(searchText) ||
                                   s.getCollegeName().toLowerCase().contains(searchText))
                        .toList();
                    break;
            }

            studentData.clear();
            studentData.addAll(filteredStudents);
            statusLabel.setText("鎼滅储鍒?" + filteredStudents.size() + " 鏉¤褰?);
            showMessage("鎼滅储瀹屾垚", "success");

        } catch (Exception e) {
            logger.error("鎼滅储瀛︾敓澶辫触", e);
            showMessage("鎼滅储澶辫触锛? + e.getMessage(), "error");
        }
    }

    /**
     * 鏄剧ず娑堟伅锛堝甫绫诲瀷锛?
     */
    private void showMessage(String message, String type) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().removeAll("success", "error", "warning");
        messageLabel.getStyleClass().add(type);

        // 3绉掑悗娓呴櫎娑堟伅
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> {
                    messageLabel.setText("灏辩华");
                    messageLabel.getStyleClass().removeAll("success", "error", "warning");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * 楠岃瘉瀛﹀彿鏍煎紡锛堜腑鍖楀ぇ瀛﹀鍙锋牸寮忥細24寮€澶?浣嶆暟瀛楋級
     */
    private boolean isValidStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        // 瀛﹀彿闀垮害搴旇鍦?-12浣嶄箣闂?
        if (studentId.length() < 6 || studentId.length() > 12) {
            return false;
        }
        // 妫€鏌ユ槸鍚﹂兘鏄暟瀛楁垨瀛楁瘝鐨勭粍鍚堬紙鍏佽瀛楁瘝濡傝绠楁満涓撲笟绛夛級
        return studentId.matches("[a-zA-Z0-9]+");
    }

    /**
     * 妫€鏌ュ簥浣嶆槸鍚﹀凡琚崰鐢?
     */
    private boolean isBedOccupied(String bedId) {
        if (bedId == null || bedId.trim().isEmpty()) {
            return false;
        }

        try {
            // 鑾峰彇鎵€鏈夊鐢燂紝妫€鏌ユ槸鍚︽湁瀛︾敓鍗犵敤姝ゅ簥浣?
            StudentController studentController = new StudentController();
            List<Student> allStudents = studentController.getAllStudents();
            for (Student student : allStudents) {
                if (bedId.equals(student.getBedId())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("妫€鏌ュ簥浣嶅崰鐢ㄧ姸鎬佸け璐? {}", bedId, e);
            return false; // 鍑洪敊鏃跺亣璁炬湭鍗犵敤锛岄伩鍏嶈鍒?
        }
    }

    /**
     * 鍒嗛〉鐩稿叧鏂规硶锛堟殏鏃剁畝鍖栵級
     */
    @FXML
    private void goToFirstPage(ActionEvent event) {
        // TODO: 瀹炵幇鍒嗛〉鍔熻兘
        showMessage("鍒嗛〉鍔熻兘鏆傛湭瀹炵幇", "info");
    }

    @FXML
    private void goToPrevPage(ActionEvent event) {
        // TODO: 瀹炵幇鍒嗛〉鍔熻兘
        showMessage("鍒嗛〉鍔熻兘鏆傛湭瀹炵幇", "info");
    }

    @FXML
    private void goToNextPage(ActionEvent event) {
        // TODO: 瀹炵幇鍒嗛〉鍔熻兘
        showMessage("鍒嗛〉鍔熻兘鏆傛湭瀹炵幇", "info");
    }

    @FXML
    private void goToLastPage(ActionEvent event) {
        // TODO: 瀹炵幇鍒嗛〉鍔熻兘
        showMessage("鍒嗛〉鍔熻兘鏆傛湭瀹炵幇", "info");
    }

    @FXML
    private void changePageSize(ActionEvent event) {
        // TODO: 瀹炵幇鍒嗛〉鍔熻兘
        showMessage("鍒嗛〉鍔熻兘鏆傛湭瀹炵幇", "info");
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        showMessage(message, "info");
    }

    /**
     * 璁板綍璋冭瘯浜嬩欢
     */
    private void logDebugEvent(String location, String message, java.util.Map<String, Object> data, String hypothesisId) {
        try {
            String logEntry = String.format("{\"id\":\"log_%d_%s\",\"timestamp\":%d,\"location\":\"%s\",\"message\":\"%s\",\"data\":%s,\"sessionId\":\"debug-session\",\"runId\":\"initial-test\",\"hypothesisId\":\"%s\"}",
                System.currentTimeMillis(),
                java.util.UUID.randomUUID().toString().substring(0, 6),
                System.currentTimeMillis(),
                location,
                message,
                data.toString().replace("=", "\":\"").replace("{", "{\"").replace("}", "\"}").replace(", ", "\",\""),
                hypothesisId
            );
            java.nio.file.Files.writeString(
                java.nio.file.Paths.get("c:\\Users\\浠讳竾鍗歕\Desktop\\璇捐\\dormitory-management-system\\.cursor\\debug.log"),
                logEntry + "\n",
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND
            );
        } catch (Exception e) {
            logger.warn("璁板綍璋冭瘯鏃ュ織澶辫触", e);
        }
    }
}

========================================
StudentRepairController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.dorm.controller.RepairApplicationController;
import org.dorm.model.entity.RepairApplication;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 瀛︾敓缁翠慨鐢宠鐣岄潰鎺у埗鍣?
 */
public class StudentRepairController {
    private static final Logger logger = LoggerFactory.getLogger(StudentRepairController.class);

    @FXML
    private Button submitButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<RepairApplication> repairTable;
    @FXML
    private TableColumn<RepairApplication, String> applyIdColumn;
    @FXML
    private TableColumn<RepairApplication, String> faultLocationColumn;
    @FXML
    private TableColumn<RepairApplication, String> faultDescColumn;
    @FXML
    private TableColumn<RepairApplication, String> applyTimeColumn;
    @FXML
    private TableColumn<RepairApplication, String> statusColumn;
    @FXML
    private TableColumn<RepairApplication, String> handlerColumn;
    @FXML
    private TableColumn<RepairApplication, String> finishTimeColumn;

    private RepairApplicationController repairController = new RepairApplicationController();
    private User currentUser;
    private ObservableList<RepairApplication> repairData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // 鍒濆鍖栬〃鏍煎垪
        applyIdColumn.setCellValueFactory(new PropertyValueFactory<>("applyId"));
        faultLocationColumn.setCellValueFactory(new PropertyValueFactory<>("faultLocation"));
        faultDescColumn.setCellValueFactory(new PropertyValueFactory<>("faultDesc"));
        applyTimeColumn.setCellValueFactory(cellData -> {
            RepairApplication app = cellData.getValue();
            String timeStr = app.getApplyTime() != null ?
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(app.getApplyTime()) : "";
            return new javafx.beans.property.SimpleStringProperty(timeStr);
        });
        statusColumn.setCellValueFactory(cellData -> {
            String status = cellData.getValue().getStatus();
            // 灏嗚嫳鏂囩姸鎬佽浆鎹负涓枃鏄剧ず
            String displayStatus;
            switch (status) {
                case "pending":
                    displayStatus = "寰呭彈鐞?;
                    break;
                case "accepted":
                    displayStatus = "宸插彈鐞?;
                    break;
                case "repairing":
                    displayStatus = "缁翠慨涓?;
                    break;
                case "completed":
                    displayStatus = "宸插畬鎴?;
                    break;
                default:
                    displayStatus = status;
                    break;
            }
            return new javafx.beans.property.SimpleStringProperty(displayStatus);
        });
        handlerColumn.setCellValueFactory(new PropertyValueFactory<>("handler"));
        finishTimeColumn.setCellValueFactory(cellData -> {
            RepairApplication app = cellData.getValue();
            String timeStr = app.getFinishTime() != null ?
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(app.getFinishTime()) : "";
            return new javafx.beans.property.SimpleStringProperty(timeStr);
        });

        // 璁剧疆琛ㄦ牸鏁版嵁
        repairTable.setItems(repairData);

        logger.info("瀛︾敓缁翠慨鐢宠鐣岄潰鍒濆鍖栧畬鎴?);
    }

    /**
     * 璁剧疆褰撳墠鐢ㄦ埛
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        loadRepairApplications();
    }

    /**
     * 鍔犺浇缁翠慨鐢宠鏁版嵁
     */
    private void loadRepairApplications() {
        if (currentUser == null) {
            showMessage("鐢ㄦ埛淇℃伅鏈缃?);
            return;
        }

        try {
            List<RepairApplication> applications = repairController.getStudentApplications(currentUser.getUserId());
            repairData.clear();
            repairData.addAll(applications);

            statusLabel.setText("鍏? + applications.size() + "鏉¤褰?);
            showMessage("鏁版嵁鍔犺浇瀹屾垚");

            logger.info("鍔犺浇瀛︾敓缁翠慨鐢宠鏁版嵁鎴愬姛锛歿}鏉¤褰?, applications.size());

        } catch (Exception e) {
            logger.error("鍔犺浇缁翠慨鐢宠鏁版嵁澶辫触", e);
            showMessage("鍔犺浇鏁版嵁澶辫触锛? + e.getMessage());
        }
    }

    /**
     * 鏄剧ず鎻愪氦缁翠慨鐢宠瀵硅瘽妗?
     */
    @FXML
    private void showSubmitDialog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/submit_repair.fxml"));
            Parent root = loader.load();

            // 鑾峰彇鎻愪氦缁翠慨鐢宠鎺у埗鍣ㄥ苟浼犻€掑綋鍓嶇敤鎴蜂俊鎭?
            SubmitRepairController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("鎻愪氦缁翠慨鐢宠");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            // 璁剧疆瀵硅瘽妗嗗叧闂悗鐨勫洖璋?
            stage.setOnHidden(e -> loadRepairApplications());

            stage.showAndWait();

        } catch (IOException e) {
            logger.error("鍔犺浇鎻愪氦缁翠慨鐢宠鐣岄潰澶辫触", e);
            showMessage("鎵撳紑鎻愪氦鐣岄潰澶辫触");
        }
    }

    /**
     * 鍒锋柊缁翠慨鐢宠鍒楄〃
     */
    @FXML
    private void refreshApplications(ActionEvent event) {
        loadRepairApplications();
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
        // 3绉掑悗娓呴櫎娑堟伅
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> messageLabel.setText(""));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

========================================
SubmitRepairController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dorm.controller.RepairApplicationController;
import org.dorm.model.entity.RepairApplication;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 鎻愪氦缁翠慨鐢宠瀵硅瘽妗嗘帶鍒跺櫒
 */
public class SubmitRepairController {
    private static final Logger logger = LoggerFactory.getLogger(SubmitRepairController.class);

    @FXML
    private TextField faultLocationField;
    @FXML
    private TextArea faultDescArea;
    @FXML
    private TextField contactPhoneField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button viewApplicationsButton;

    private RepairApplicationController repairController = new RepairApplicationController();
    private User currentUser;

    @FXML
    private void initialize() {
        logger.info("鎻愪氦缁翠慨鐢宠瀵硅瘽妗嗗垵濮嬪寲瀹屾垚");
    }

    /**
     * 璁剧疆褰撳墠鐢ㄦ埛
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * 澶勭悊鎻愪氦鎸夐挳鐐瑰嚮浜嬩欢
     */
    @FXML
    private void handleSubmit(ActionEvent event) {
        // 鑾峰彇杈撳叆鏁版嵁
        String faultLocation = faultLocationField.getText().trim();
        String faultDesc = faultDescArea.getText().trim();
        String contactPhone = contactPhoneField.getText().trim();

        // 楠岃瘉杈撳叆
        if (faultLocation.isEmpty()) {
            showMessage("璇疯緭鍏ユ晠闅滀綅缃紒");
            faultLocationField.requestFocus();
            return;
        }

        if (faultDesc.isEmpty()) {
            showMessage("璇疯緭鍏ユ晠闅滄弿杩帮紒");
            faultDescArea.requestFocus();
            return;
        }

        if (contactPhone.isEmpty()) {
            showMessage("璇疯緭鍏ヨ仈绯荤數璇濓紒");
            contactPhoneField.requestFocus();
            return;
        }

        // 楠岃瘉鐢佃瘽鍙风爜鏍煎紡锛堢畝鍗曢獙璇侊級
        if (!contactPhone.matches("^1[3-9]\\d{9}$")) {
            showMessage("璇疯緭鍏ユ纭殑鎵嬫満鍙风爜锛?);
            contactPhoneField.requestFocus();
            return;
        }

        // 鍒涘缓缁翠慨鐢宠瀵硅薄
        RepairApplication application = new RepairApplication();
        application.setStudentId(currentUser.getUserId());
        application.setFaultLocation(faultLocation);
        application.setFaultDesc(faultDesc);
        application.setContactPhone(contactPhone);

        // 绂佺敤鎻愪氦鎸夐挳
        submitButton.setDisable(true);
        submitButton.setText("鎻愪氦涓?..");

        try {
            // 鎻愪氦缁翠慨鐢宠
            boolean result = repairController.submitRepairApplication(application);

            if (result) {
                showMessage("缁翠慨鐢宠鎻愪氦鎴愬姛锛?);
                logger.info("缁翠慨鐢宠鎻愪氦鎴愬姛锛歿} - {}", currentUser.getUserId(), faultLocation);

                // 绂佺敤鎵€鏈夎緭鍏ユ帶浠讹紝闃叉閲嶅鎻愪氦
                faultLocationField.setDisable(true);
                faultDescArea.setDisable(true);
                contactPhoneField.setDisable(true);
                submitButton.setDisable(true);
                submitButton.setText("鎻愪氦鎴愬姛");

                // 鏄剧ず鏌ョ湅鐢宠鎸夐挳
                viewApplicationsButton.setVisible(true);
                cancelButton.setText("杩斿洖");
                cancelButton.setOnAction(e -> returnToMain());
            } else {
                showMessage("缁翠慨鐢宠鎻愪氦澶辫触锛岃绋嶅悗閲嶈瘯锛?);
                submitButton.setDisable(false);
                submitButton.setText("鎻愪氦");
            }

        } catch (Exception e) {
            logger.error("鎻愪氦缁翠慨鐢宠杩囩▼涓彂鐢熷紓甯?, e);
            showMessage("鎻愪氦澶辫触锛? + e.getMessage());
            submitButton.setDisable(false);
            submitButton.setText("鎻愪氦");
        }
    }

    /**
     * 澶勭悊鍙栨秷鎸夐挳鐐瑰嚮浜嬩欢
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        logger.info("鐢ㄦ埛鍙栨秷鎻愪氦缁翠慨鐢宠");
        closeDialog();
    }

    /**
     * 澶勭悊鏌ョ湅鐢宠鎸夐挳鐐瑰嚮浜嬩欢
     */
    @FXML
    private void handleViewApplications(ActionEvent event) {
        logger.info("鐢ㄦ埛鐐瑰嚮鏌ョ湅鐢宠鎸夐挳");
        showMessage("璇风偣鍑诲乏渚ц彍鍗曠殑'鎴戠殑缁翠慨鐢宠'鏉ユ煡鐪嬫偍鐨勭敵璇疯褰?);
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * 杩斿洖涓荤晫闈?
     */
    private void returnToMain() {
        // 杩欓噷鍙互閫氱煡鐖舵帶鍒跺櫒杩斿洖涓荤晫闈?
        // 鐢变簬杩欐槸鍔犺浇鍦ㄥ唴瀹瑰尯鍩熶腑鐨勶紝鎴戜滑鍙互閫氳繃浜嬩欢閫氱煡鎴栬€呯洿鎺ユ搷浣?
        logger.info("杩斿洖瀛︾敓涓荤晫闈?);

        // 灏濊瘯閫氳繃鍦烘櫙鏌ユ壘鐖舵帶鍒跺櫒骞跺埛鏂板唴瀹?
        // 杩欓噷鎴戜滑绠€鍗曞湴绂佺敤鐣岄潰锛岃鐢ㄦ埛鐭ラ亾鎿嶄綔宸插畬鎴?
        showMessage("鎿嶄綔瀹屾垚锛岃杩斿洖涓荤晫闈㈡煡鐪嬬敵璇疯褰?);
    }

    /**
     * 鍏抽棴瀵硅瘽妗嗭紙宸插簾寮冿紝涓嶅啀浣跨敤锛?
     */
    @Deprecated
    private void closeDialog() {
        // 杩欎釜鏂规硶涓嶅啀浣跨敤锛屽洜涓鸿繖涓晫闈笉鏄嫭绔嬬獥鍙?
        logger.warn("closeDialog鏂规硶宸茶搴熷純锛屾鐣岄潰涓嶅簲鍏抽棴绐楀彛");
    }
}

========================================
ViolationManagementController.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm\view
========================================
package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import org.dorm.controller.StudentController;
import org.dorm.controller.ViolationController;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.Violation;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * 杩濈邯绠＄悊鐣岄潰鎺у埗鍣?
 */
public class ViolationManagementController {

    private boolean isStudentMode = false;
    private String currentStudentId;

    /**
     * 璁剧疆涓哄鐢熸ā寮忥紝鍙樉绀烘寚瀹氬鐢熺殑杩濈邯璁板綍
     */
    public void setStudentMode(String studentId) {
        this.isStudentMode = true;
        this.currentStudentId = studentId;

        // 鍦ㄥ鐢熸ā寮忎笅闅愯棌绠＄悊鍔熻兘
        if (addButton != null) addButton.setVisible(false);
        if (editButton != null) editButton.setVisible(false);
        if (deleteButton != null) deleteButton.setVisible(false);

        // 閲嶆柊鍔犺浇鏁版嵁
        loadViolations();
    }
    private static final Logger logger = LoggerFactory.getLogger(ViolationManagementController.class);

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> searchTypeCombo;
    @FXML
    private Label statusLabel;
    @FXML
    private Label selectedLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label lastUpdateLabel;
    @FXML
    private TableView<Violation> violationTable;
    @FXML
    private TableColumn<Violation, String> violationIdColumn;
    @FXML
    private TableColumn<Violation, String> studentIdColumn;
    @FXML
    private TableColumn<Violation, String> studentNameColumn;
    @FXML
    private TableColumn<Violation, String> descriptionColumn;
    @FXML
    private TableColumn<Violation, String> violationDateColumn;
    @FXML
    private TableColumn<Violation, String> penaltyColumn;

    // 鍒嗛〉鐩稿叧
    @FXML
    private Button firstPageButton;
    @FXML
    private Button prevPageButton;
    @FXML
    private Button nextPageButton;
    @FXML
    private Button lastPageButton;
    @FXML
    private Label pageInfoLabel;
    @FXML
    private ComboBox<String> pageSizeCombo;

    private ObservableList<Violation> violationData = FXCollections.observableArrayList();
    private ViolationController violationController = new ViolationController();
    private StudentController studentController = new StudentController();

    // 鍒嗛〉鐩稿叧鍙橀噺
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalPages = 1;
    private int totalRecords = 0;

    @FXML
    private void initialize() {
        // 鍒濆鍖栬〃鏍煎垪
        violationIdColumn.setCellValueFactory(new PropertyValueFactory<>("violationId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        // 瀛︾敓濮撳悕鍒楅渶瑕佺壒娈婂鐞嗭紝閫氳繃瀛﹀彿鏌ユ壘濮撳悕
        studentNameColumn.setCellValueFactory(cellData -> {
            Violation violation = cellData.getValue();
            try {
                Student student = studentController.getStudentById(violation.getStudentId());
                return new javafx.beans.property.SimpleStringProperty(
                    student != null ? student.getName() : "鏈煡");
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty("鏈煡");
            }
        });

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // 杩濈邯鏃ユ湡鏍煎紡鍖?
        violationDateColumn.setCellValueFactory(cellData -> {
            Violation violation = cellData.getValue();
            java.util.Date date = violation.getViolationDate();
            if (date != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return new javafx.beans.property.SimpleStringProperty(sdf.format(date));
            } else {
                return new javafx.beans.property.SimpleStringProperty("");
            }
        });

        penaltyColumn.setCellValueFactory(new PropertyValueFactory<>("penalty"));

        // 璁剧疆琛ㄦ牸鏁版嵁
        violationTable.setItems(violationData);

        // 鍒濆鍖栨悳绱㈢被鍨?
        if (searchTypeCombo != null) {
            searchTypeCombo.setValue("鍏ㄩ儴");
        }

        // 鍒濆鍖栭〉闈㈠ぇ灏?
        if (pageSizeCombo != null) {
            pageSizeCombo.setValue("10");
        }

        // 鍒濆鍖栬〃鏍奸€夋嫨鐩戝惉鍣?
        if (violationTable != null) {
            violationTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                updateSelectedCount();
            });
        }

        // 鍒濆鍖栧垎椤垫寜閽姸鎬?
        updatePaginationButtons();

        // 鍔犺浇鍒濆鏁版嵁
        loadViolations();

        logger.info("杩濈邯绠＄悊鐣岄潰鍒濆鍖栧畬鎴?);
    }

    /**
     * 鍔犺浇杩濈邯鏁版嵁
     */
    private void loadViolations() {
        try {
            List<Violation> violations;

            if (isStudentMode && currentStudentId != null) {
                // 瀛︾敓妯″紡锛氬彧鍔犺浇褰撳墠瀛︾敓鐨勮繚绾褰?
                violations = violationController.getViolationsByStudentId(currentStudentId);
            } else {
                // 绠＄悊鍛樻ā寮忥細鍔犺浇鎵€鏈夎繚绾褰?
                violations = violationController.getAllViolations();
            }

            violationData.clear();
            violationData.addAll(violations);

            totalRecords = violations.size();
            totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            // 鏇存柊鐘舵€佹樉绀?
            statusLabel.setText("鍏?" + totalRecords + " 鏉¤褰?);
            updateLastUpdateTime();
            updatePageInfo();
            showMessage("鏁版嵁鍔犺浇瀹屾垚", "success");

            logger.info("鍔犺浇杩濈邯鏁版嵁鎴愬姛锛歿}鏉¤褰?, violations.size());

        } catch (Exception e) {
            logger.error("鍔犺浇杩濈邯鏁版嵁澶辫触", e);
            showMessage("鍔犺浇鏁版嵁澶辫触锛? + e.getMessage(), "error");
        }
    }

    /**
     * 鏇存柊鍒嗛〉鎸夐挳鐘舵€?
     */
    private void updatePaginationButtons() {
        // 鏆傛椂绂佺敤鎵€鏈夊垎椤垫寜閽紝鍥犱负鍒嗛〉鍔熻兘杩樻湭瀹屽叏瀹炵幇
        if (firstPageButton != null) firstPageButton.setDisable(true);
        if (prevPageButton != null) prevPageButton.setDisable(true);
        if (nextPageButton != null) nextPageButton.setDisable(true);
        if (lastPageButton != null) lastPageButton.setDisable(true);
    }

    /**
     * 鏇存柊椤甸潰淇℃伅
     */
    private void updatePageInfo() {
        if (pageInfoLabel != null) {
            pageInfoLabel.setText("绗?" + currentPage + " 椤?/ 鍏?" + Math.max(1, totalPages) + " 椤?);
        }
    }

    /**
     * 鏇存柊鏈€鍚庢洿鏂版椂闂?
     */
    private void updateLastUpdateTime() {
        if (lastUpdateLabel != null) {
            lastUpdateLabel.setText("鏈€鍚庢洿鏂? " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
    }

    /**
     * 鏇存柊閫変腑椤硅鏁?
     */
    private void updateSelectedCount() {
        if (violationTable != null && selectedLabel != null) {
            int selectedCount = violationTable.getSelectionModel().getSelectedItems().size();
            selectedLabel.setText("宸查€夋嫨 " + selectedCount + " 椤?);
        }
    }

    /**
     * 鏄剧ず娣诲姞杩濈邯璁板綍瀵硅瘽妗?
     */
    @FXML
    private void showAddDialog(ActionEvent event) {
        // 鍒涘缓瀵硅瘽妗?
        Dialog<Violation> dialog = new Dialog<>();
        dialog.setTitle("娣诲姞杩濈邯璁板綍");
        dialog.setHeaderText("璇疯緭鍏ユ柊鐨勮繚绾褰曚俊鎭?);

        // 璁剧疆鎸夐挳
        ButtonType saveButtonType = new ButtonType("淇濆瓨", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // 琛ㄥ崟鎺т欢
        ComboBox<String> studentCombo = new ComboBox<>();
        TextArea descriptionArea = new TextArea();
        DatePicker violationDatePicker = new DatePicker();
        TextField penaltyField = new TextField();

        // 鍒濆鍖栧鐢熶笅鎷夋
        try {
            List<Student> students = studentController.getAllStudents();
            for (Student student : students) {
                studentCombo.getItems().add(student.getStudentId() + " - " + student.getName());
            }
        } catch (Exception e) {
            logger.error("鍔犺浇瀛︾敓鍒楄〃澶辫触", e);
        }

        descriptionArea.setPrefRowCount(3);
        descriptionArea.setWrapText(true);

        // 璁剧疆鏃ユ湡榛樿鍊间负浠婂ぉ
        violationDatePicker.setValue(java.time.LocalDate.now());

        grid.add(new Label("瀛︾敓:"), 0, 0);
        grid.add(studentCombo, 1, 0);
        grid.add(new Label("杩濈邯鎻忚堪:"), 0, 1);
        grid.add(descriptionArea, 1, 1);
        grid.add(new Label("杩濈邯鏃ユ湡:"), 0, 2);
        grid.add(violationDatePicker, 1, 2);
        grid.add(new Label("澶勭綒鎺柦:"), 0, 3);
        grid.add(penaltyField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // 杞崲缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    Violation violation = new Violation();

                    // 鐢熸垚杩濈邯鍗曞彿
                    String violationId = "VIO" + System.currentTimeMillis();
                    violation.setViolationId(violationId);

                    // 瑙ｆ瀽瀛︾敓ID
                    String selectedStudent = studentCombo.getValue();
                    if (selectedStudent != null && selectedStudent.contains(" - ")) {
                        String studentId = selectedStudent.split(" - ")[0];
                        violation.setStudentId(studentId);
                    }

                    // 璁剧疆鍏朵粬瀛楁
                    violation.setDescription(descriptionArea.getText());
                    violation.setViolationDate(java.sql.Date.valueOf(violationDatePicker.getValue()));
                    violation.setPenalty(penaltyField.getText());

                    return violation;
                } catch (Exception e) {
                    logger.error("鍒涘缓杩濈邯璁板綍澶辫触", e);
                    return null;
                }
            }
            return null;
        });

        Optional<Violation> result = dialog.showAndWait();
        result.ifPresent(violation -> {
            try {
                boolean success = violationController.addViolation(violation);
                if (success) {
                    showMessage("杩濈邯璁板綍娣诲姞鎴愬姛锛?);
                    loadViolations();
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyViolationDataChanged("add");
                    logger.info("娣诲姞杩濈邯璁板綍鎴愬姛锛歿}", violation.getViolationId());
                } else {
                    showMessage("杩濈邯璁板綍娣诲姞澶辫触锛?);
                }
            } catch (Exception e) {
                logger.error("娣诲姞杩濈邯璁板綍寮傚父", e);
                showMessage("娣诲姞澶辫触锛? + e.getMessage());
            }
        });
    }

    /**
     * 鏄剧ず缂栬緫杩濈邯璁板綍瀵硅瘽妗?
     */
    @FXML
    private void showEditDialog(ActionEvent event) {
        Violation selectedViolation = violationTable.getSelectionModel().getSelectedItem();
        if (selectedViolation == null) {
            showMessage("璇峰厛閫夋嫨瑕佺紪杈戠殑杩濈邯璁板綍锛?);
            return;
        }

        // 鍒涘缓瀵硅瘽妗?
        Dialog<Violation> dialog = new Dialog<>();
        dialog.setTitle("缂栬緫杩濈邯璁板綍");
        dialog.setHeaderText("淇敼杩濈邯璁板綍淇℃伅");

        // 璁剧疆鎸夐挳
        ButtonType saveButtonType = new ButtonType("淇濆瓨", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 鍒涘缓琛ㄥ崟
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // 琛ㄥ崟鎺т欢
        Label studentLabel = new Label(selectedViolation.getStudentId() + " - " +
            (studentController.getStudentById(selectedViolation.getStudentId()) != null ?
             studentController.getStudentById(selectedViolation.getStudentId()).getName() : "鏈煡"));
        TextArea descriptionArea = new TextArea(selectedViolation.getDescription());
        DatePicker violationDatePicker = new DatePicker();
        TextField penaltyField = new TextField(selectedViolation.getPenalty());

        descriptionArea.setPrefRowCount(3);
        descriptionArea.setWrapText(true);

        // 璁剧疆鏃ユ湡
        if (selectedViolation.getViolationDate() != null) {
            violationDatePicker.setValue(selectedViolation.getViolationDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        } else {
            violationDatePicker.setValue(java.time.LocalDate.now());
        }

        grid.add(new Label("瀛︾敓:"), 0, 0);
        grid.add(studentLabel, 1, 0);
        grid.add(new Label("杩濈邯鎻忚堪:"), 0, 1);
        grid.add(descriptionArea, 1, 1);
        grid.add(new Label("杩濈邯鏃ユ湡:"), 0, 2);
        grid.add(violationDatePicker, 1, 2);
        grid.add(new Label("澶勭綒鎺柦:"), 0, 3);
        grid.add(penaltyField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // 杞崲缁撴灉
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    Violation violation = new Violation();
                    violation.setViolationId(selectedViolation.getViolationId());
                    violation.setStudentId(selectedViolation.getStudentId());

                    violation.setDescription(descriptionArea.getText());
                    violation.setViolationDate(java.sql.Date.valueOf(violationDatePicker.getValue()));
                    violation.setPenalty(penaltyField.getText());

                    return violation;
                } catch (Exception e) {
                    logger.error("鏇存柊杩濈邯璁板綍澶辫触", e);
                    return null;
                }
            }
            return null;
        });

        Optional<Violation> result = dialog.showAndWait();
        result.ifPresent(violation -> {
            try {
                boolean success = violationController.updateViolation(violation);
                if (success) {
                    showMessage("杩濈邯璁板綍鏇存柊鎴愬姛锛?);
                    loadViolations();
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyViolationDataChanged("update");
                    logger.info("鏇存柊杩濈邯璁板綍鎴愬姛锛歿}", violation.getViolationId());
                } else {
                    showMessage("杩濈邯璁板綍鏇存柊澶辫触锛?);
                }
            } catch (Exception e) {
                logger.error("鏇存柊杩濈邯璁板綍寮傚父", e);
                showMessage("鏇存柊澶辫触锛? + e.getMessage());
            }
        });
    }

    /**
     * 鍒犻櫎杩濈邯璁板綍
     */
    @FXML
    private void deleteViolation(ActionEvent event) {
        Violation selectedViolation = violationTable.getSelectionModel().getSelectedItem();
        if (selectedViolation == null) {
            showMessage("璇峰厛閫夋嫨瑕佸垹闄ょ殑杩濈邯璁板綍锛?);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("纭鍒犻櫎");
        alert.setHeaderText("纭畾瑕佸垹闄よ繖鏉¤繚绾褰曞悧锛?);
        alert.setContentText("杩濈邯鍗曞彿: " + selectedViolation.getViolationId() + "\n瀛︾敓: " + selectedViolation.getStudentId());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean success = violationController.deleteViolation(selectedViolation.getViolationId());
                if (success) {
                    showMessage("杩濈邯璁板綍鍒犻櫎鎴愬姛锛?);
                    loadViolations();
                    // 閫氱煡鍏朵粬鐣岄潰鏁版嵁宸叉洿鏂?
                    DataUpdateManager.getInstance().notifyViolationDataChanged("delete");
                    logger.info("鍒犻櫎杩濈邯璁板綍鎴愬姛锛歿}", selectedViolation.getViolationId());
                } else {
                    showMessage("杩濈邯璁板綍鍒犻櫎澶辫触锛?);
                }
            } catch (Exception e) {
                logger.error("鍒犻櫎杩濈邯璁板綍寮傚父", e);
                showMessage("鍒犻櫎澶辫触锛? + e.getMessage());
            }
        }
    }

    /**
     * 鍒锋柊杩濈邯璁板綍鍒楄〃
     */
    @FXML
    private void refreshViolations() {
        loadViolations();
    }

    /**
     * 鎼滅储杩濈邯璁板綍
     */
    @FXML
    private void searchViolations(ActionEvent event) {
        String searchText = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            loadViolations(); // 濡傛灉鎼滅储涓虹┖锛屾樉绀烘墍鏈夎褰?
            return;
        }

        try {
            List<Violation> allViolations = violationController.getAllViolations();
            List<Violation> filteredViolations;

            switch (searchType) {
                case "瀛﹀彿":
                    filteredViolations = allViolations.stream()
                        .filter(v -> v.getStudentId().contains(searchText))
                        .toList();
                    break;
                case "濮撳悕":
                    filteredViolations = allViolations.stream()
                        .filter(v -> {
                            try {
                                Student student = studentController.getStudentById(v.getStudentId());
                                return student != null && student.getName().contains(searchText);
                            } catch (Exception e) {
                                return false;
                            }
                        })
                        .toList();
                    break;
                default:
                    // 鍏ㄩ儴鎼滅储
                    filteredViolations = allViolations.stream()
                        .filter(v -> v.getStudentId().contains(searchText) ||
                                   v.getDescription() != null && v.getDescription().contains(searchText) ||
                                   v.getPenalty() != null && v.getPenalty().contains(searchText))
                        .toList();
                    break;
            }

            violationData.clear();
            violationData.addAll(filteredViolations);
            statusLabel.setText("鎼滅储鍒?" + filteredViolations.size() + " 鏉¤褰?);
            showMessage("鎼滅储瀹屾垚", "success");

        } catch (Exception e) {
            logger.error("鎼滅储杩濈邯璁板綍澶辫触", e);
            showMessage("鎼滅储澶辫触锛? + e.getMessage(), "error");
        }
    }

    // 鍒嗛〉鐩稿叧鏂规硶锛堟殏鏃舵湭瀹炵幇锛?
    @FXML
    private void goToFirstPage() {}
    @FXML
    private void goToPrevPage() {}
    @FXML
    private void goToNextPage() {}
    @FXML
    private void goToLastPage() {}
    @FXML
    private void changePageSize(ActionEvent event) {}

    /**
     * 鏄剧ず娑堟伅锛堝甫绫诲瀷锛?
     */
    private void showMessage(String message, String type) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().removeAll("success", "error", "warning");
        messageLabel.getStyleClass().add(type);

        // 3绉掑悗娓呴櫎娑堟伅
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> {
                    messageLabel.setText("灏辩华");
                    messageLabel.getStyleClass().removeAll("success", "error", "warning");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * 鏄剧ず娑堟伅
     */
    private void showMessage(String message) {
        showMessage(message, "info");
    }
}

========================================
Main.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\dorm
========================================
package org.dorm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 瀹胯垗绠＄悊绯荤粺涓诲簲鐢ㄧ▼搴忕被
 */
public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) {
        try {
            // 娴嬭瘯鏁版嵁搴撹繛鎺?
            if (!DatabaseUtil.testConnection()) {
                logger.error("鏁版嵁搴撹繛鎺ュけ璐ワ紝璇锋鏌ユ暟鎹簱閰嶇疆鍜孧ySQL鏈嶅姟");
                // 鏄剧ず閿欒瀵硅瘽妗?
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("鏁版嵁搴撹繛鎺ラ敊璇?);
                alert.setHeaderText("鏃犳硶杩炴帴鍒版暟鎹簱");
                alert.setContentText("璇锋鏌ワ細\n1. MySQL鏈嶅姟鏄惁鍚姩\n2. 鏁版嵁搴撻厤缃槸鍚︽纭甛n3. 鏁版嵁搴撴槸鍚﹀瓨鍦?);
                alert.showAndWait();
                System.exit(1);
                return;
            }

            // 鍔犺浇鐧诲綍鐣岄潰
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 800); // 璁剧疆鏇村ぇ鐨勭獥鍙ｅ昂瀵?

            primaryStage.setTitle("瀹胯垗绠＄悊绯荤粺 - 鐧诲綍");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true); // 鍏佽璋冩暣绐楀彛澶у皬
            primaryStage.setMinWidth(1000); // 璁剧疆鏈€灏忓搴?
            primaryStage.setMinHeight(700); // 璁剧疆鏈€灏忛珮搴?
            primaryStage.centerOnScreen(); // 绐楀彛灞呬腑鏄剧ず
            primaryStage.show();

            logger.info("瀹胯垗绠＄悊绯荤粺鍚姩鎴愬姛");
        } catch (IOException e) {
            logger.error("鍔犺浇鐧诲綍鐣岄潰澶辫触", e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

========================================
Main.java - C:\Users\任万博\Desktop\课设最终\Rwb-dormitory-management-system\src\main\java\org\example
========================================
package org.example;

//TIP 瑕?b>杩愯</b>浠ｇ爜锛岃鎸?<shortcut actionId="Run"/> 鎴?// 鐐瑰嚮瑁呰鍖哄煙涓殑 <icon src="AllIcons.Actions.Execute"/> 鍥炬爣銆?public class Main {
    public static void main(String[] args) {
        //TIP 褰撴枃鏈厜鏍囦綅浜庨珮浜樉绀虹殑鏂囨湰澶勬椂鎸?<shortcut actionId="ShowIntentionActions"/>
        // 鏌ョ湅 IntelliJ IDEA 寤鸿濡備綍淇銆?        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP 鎸?<shortcut actionId="Debug"/> 寮€濮嬭皟璇曚唬鐮併€傛垜浠凡缁忚缃簡涓€涓?<icon src="AllIcons.Debugger.Db_set_breakpoint"/> 鏂偣
            // 浣嗘偍濮嬬粓鍙互閫氳繃鎸?<shortcut actionId="ToggleLineBreakpoint"/> 娣诲姞鏇村鏂偣銆?            System.out.println("i = " + i);
        }
    }
}
