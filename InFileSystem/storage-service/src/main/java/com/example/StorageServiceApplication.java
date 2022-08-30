package com.example;

import com.example.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/files")
@SpringBootApplication
public class StorageServiceApplication {

	@Autowired
	private FileDataService service;

	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image")MultipartFile file) throws Exception {
		String uploadImage = service.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData = service.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}

	public static void main(String[] args) {
		SpringApplication.run(StorageServiceApplication.class, args);
	}

}
