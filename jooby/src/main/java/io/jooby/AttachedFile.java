package io.jooby;

import org.apache.commons.io.FilenameUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;

public class AttachedFile {

  private static final String CONTENT_DISPOSITION = "attachment;filename=\"%s\"";
  private static final String FILENAME_STAR = ";filename*=%s''%s";

  private static final String CHARSET = "UTF-8";

  private final long length;
  private final MediaType contentType;

  private String filename;

  private String contentDisposition;

  private InputStream content;

  public AttachedFile(String filename, InputStream content, long length) {
    try {
      this.filename = FilenameUtils.getName(filename);
      this.contentType = MediaType.byFile(this.filename);
      String filenameStar = URLEncoder.encode(this.filename, CHARSET).replaceAll("\\+", "%20");
      if (this.filename.equals(filenameStar)) {
        this.contentDisposition = String.format(CONTENT_DISPOSITION, this.filename);
      } else {
        this.contentDisposition = String.format(CONTENT_DISPOSITION, this.filename) + String
            .format(FILENAME_STAR, CHARSET, filenameStar);
      }
      this.content = content;
      this.length = length;
    } catch (UnsupportedEncodingException x) {
      throw Throwing.sneakyThrow(x);
    }
  }

  public AttachedFile(String filename, InputStream content) {
    this(filename, content, -1);
  }

  public AttachedFile(String filename, Path file) throws IOException {
    this(filename, new FileInputStream(file.toFile()), Files.size(file));
  }

  public AttachedFile(Path file) throws IOException {
    this(file.getFileName().toString(), file);
  }

  public long length() {
    return length;
  }

  public MediaType contentType() {
    return contentType;
  }

  public String filename() {
    return filename;
  }

  public String contentDisposition() {
    return contentDisposition;
  }

  public InputStream content() {
    return content;
  }

  @Override public String toString() {
    return filename;
  }
}
