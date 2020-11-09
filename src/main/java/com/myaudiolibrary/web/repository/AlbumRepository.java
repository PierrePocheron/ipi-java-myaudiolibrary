package com.myaudiolibrary.web.repository;

import com.myaudiolibrary.web.model.Album;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Long>{
    Album findByAlbumId(Long id);
    List<Album> findAll();
}
