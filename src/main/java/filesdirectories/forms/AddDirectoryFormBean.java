package filesdirectories.forms;

import javax.validation.constraints.Pattern;

public class AddDirectoryFormBean {

    @Pattern(regexp = "", message = "Данная строка является недопустимым путем")
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
