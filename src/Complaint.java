public class Complaint {
    private String complaintText;
    private boolean accepted;
    private Attendance attendance;

    public Complaint() {}
    
    

    public Complaint(String complaintText, Attendance attendance) {
        this.complaintText = complaintText;
        this.accepted = false;
        this.attendance = attendance;
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

    
