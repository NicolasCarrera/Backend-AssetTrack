package com.proyecto_titulacion.assettrack.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    String uploadFile(MultipartFile file, String fileName) throws Exception;

    InputStream getFile(String fileName) throws Exception;
}
