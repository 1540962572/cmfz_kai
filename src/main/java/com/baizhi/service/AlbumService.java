package com.baizhi.service;

import com.baizhi.entity.Album;

public interface AlbumService extends BeanService<Album>{
    Album findById(Album album);
}
