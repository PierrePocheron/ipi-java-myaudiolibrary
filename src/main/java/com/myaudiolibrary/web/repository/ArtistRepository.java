package com.myaudiolibrary.web.repository;

import com.myaudiolibrary.web.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ArtistRepository extends PagingAndSortingRepository<Artist,Long>{
    Artist findByArtistId(Long id);
    Artist findByName(String name);
    Page<Artist> findAllByNameContaining(String name, Pageable pageable);
    Boolean existsByNameIgnoreCase(String name);
}
