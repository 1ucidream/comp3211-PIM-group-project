//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class PIMNote extends PIMEntity {
    String noteText;

    public PIMNote() {
    }

    public String getNoteText() {
        return this.noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public void fromString(String ex) {
    }

    public String toString() {
        return "NOTE " + this.Priority + " " + this.noteText;
    }
}
