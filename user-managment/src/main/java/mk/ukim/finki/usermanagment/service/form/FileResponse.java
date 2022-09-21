package mk.ukim.finki.usermanagment.service.form;

import lombok.Data;
import mk.ukim.finki.usermanagment.domain.model.FileMetadataId;

@Data
public class FileResponse {
    private FileMetadataId id;
    private String name;
    private String url;
    private String type;
    private String fileContentId;

    public FileResponse(FileMetadataId id, String name, String url, String type, String fileContentId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.fileContentId = fileContentId;
    }
}
