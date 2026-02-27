public class TestReminderSimple {
    public static void main(String[] args) {
        System.out.println("测试提醒功能");
        try {
            // 直接测试数据库连接
            org.dorm.util.DatabaseUtil.getConnection();
            System.out.println("数据库连接成功");

            // 测试获取提醒数据
            org.dorm.model.dao.ReminderDAO dao = new org.dorm.model.dao.impl.ReminderDAOImpl();
            java.util.List<org.dorm.model.entity.Reminder> reminders = dao.findAll();
            System.out.println("找到 " + reminders.size() + " 条提醒记录");

            for (org.dorm.model.entity.Reminder r : reminders) {
                System.out.println("提醒: " + r.getTitle() + " - " + r.getStudentName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}