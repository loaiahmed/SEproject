public class Complaint {
    private int complaintID;
    private String complaintText;
    private boolean accepted;
    private Attendance attendance;

    private static int count = 0;

    public Complaint() {}
    
    

    public Complaint(String complaintText, Attendance attendance) {
        this.complaintID = count;
        this.complaintText = complaintText;
        this.accepted = false;
        this.attendance = attendance;
        count++;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public String getComplaintText() {
        return complaintText;
    }
    
     public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }


    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintText='" + complaintText + '\'' +
                ", accepted=" + accepted +
                ", attendance=" + attendance +
                '}';
    }
}

    
