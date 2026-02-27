import org.dorm.controller.ReminderController;
import org.dorm.model.entity.Reminder;
import java.util.List;

public class TestReminder {
    public static void main(String[] args) {
        try {
            ReminderController controller = new ReminderController();
            List<Reminder> reminders = controller.getAllReminders();

            System.out.println("提醒数量: " + reminders.size());
            for (Reminder reminder : reminders) {
                System.out.println("ID: " + reminder.getReminderId());
                System.out.println("学生: " + reminder.getStudentName() + " (" + reminder.getStudentId() + ")");
                System.out.println("类型: " + reminder.getType());
                System.out.println("标题: " + reminder.getTitle());
                System.out.println("状态: " + reminder.getStatus());
                System.out.println("---");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}