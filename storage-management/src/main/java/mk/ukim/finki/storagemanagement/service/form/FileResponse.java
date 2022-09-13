package mk.ukim.finki.storagemanagement.service.form;

import lombok.Data;

@Data
public class FileResponse {
    private String name;
    private String url;
    private String type;
    private long size;

    public FileResponse(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }
}
