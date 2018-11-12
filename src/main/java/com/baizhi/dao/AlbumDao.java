package com.baizhi.dao;

import com.baizhi.entity.Album;

public interface AlbumDao extends BeanDao<Album> {
    Album queryId(Album album);
}
