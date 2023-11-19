import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Alarmer implements Serializable {

    private transient ScheduledExecutorService executorService;
    private LocalDateTime startTime;
    private LocalDateTime alarmTime;

    private String description;

    // 新增字段，表示提醒是否已被关闭
    private boolean reminderClosed;

    public Alarmer(LocalDateTime startTime, LocalDateTime alarmTime, String description) {
        this.startTime = startTime;
        this.alarmTime = alarmTime;
        this.description = description;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void setAlarmTime(LocalDateTime alarmTime){
        this.alarmTime = alarmTime;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void resetClosed(){
        reminderClosed = false;//对alramer的任何更新都会将弹窗状态置为 false, 未触发
    }
    public void scheduleAlarmer() {

        /**
         * 首先检查 executorService 是否为 null 或者已经被关闭（通过调用 isShutdown() 方法来检查线程池的状态）。如果满足任一条件，
         * 即线程池为 null 或者已经被关闭，那么会重新创建一个新的单线程的 ScheduledExecutorService。
         *
         * 这样做的目的是确保在需要调度任务时，始终有一个可用的线程池。如果线程池已经被关闭，那么需要重新创建一个新的线程池，以便能够继续调度任务
         */
        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }

        Duration delay = Duration.between(LocalDateTime.now(), alarmTime);
        long delayMillis = delay.toMillis();

        executorService.schedule(() -> {
            if (!reminderClosed) {
                //意思是, 如果弹窗没有被点击关闭
                remind();
            }
            executorService.shutdown();//关闭线程池, 目的是使得相同弹窗不重复出现?
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    //提醒信息弹窗的设置
    private void remind() {
        //这个"将于xx时间段后开始项目", 应该是弹窗出现的那一刻到start time, 这一段时间, 而不是alarm time到start time
        Duration duration = Duration.between(LocalDateTime.now(), startTime);
        String durationString = MyformatDuration(duration);
        int result = JOptionPane.showConfirmDialog(null, (description + " " + startTime + "\n" +
                "将于" + durationString + "后开始"), "AlARMING　：", JOptionPane.DEFAULT_OPTION);

        if (result == JOptionPane.OK_OPTION || result == JOptionPane.DEFAULT_OPTION) {
            //点击弹窗的"确认"按钮关闭, OK_OPTION返回值 = 0, 点击右上角 x 按钮关闭, DEFAULT_OPTION返回值 = -1
            reminderClosed = true; // 标记提醒已被关闭
        }

        System.out.println("弹窗状态: " + reminderClosed);
        PIMManager.saveData();
    }

    private String MyformatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        return days + " 天 " + hours + " 小时 " + minutes + " 分钟";
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        //由于 executorService 字段被标记为 transient，在反序列化过程中需要手动重新创建该对象
        executorService = Executors.newSingleThreadScheduledExecutor();
        scheduleAlarmer();
    }

    public String toString() {
        return  "----------------------------------------"+ "\n" +
                "---type: Alarm " + "\n" +
                "---Start Time: " + this.startTime + "\n" +
                "---Alarm Time: " + this.alarmTime+ "\n" +
                "---Description: " + this.description + "\n" +
                "---isClosed: " + this.reminderClosed + "\n" +
                "----------------------------------------";
    }

    public static void main(String[] args) {
        // 获取用户输入的开始时间和提醒时间
        LocalDateTime startTime = LocalDateTime.of(2023, 11, 20, 10, 0);
        LocalDateTime alarmTime = LocalDateTime.of(2023, 11, 18, 23, 31);

        String des = "双十一";

        Alarmer alarmer = new Alarmer(startTime, alarmTime, des);
        alarmer.scheduleAlarmer();
    }
}
