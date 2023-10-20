package com.musicwebbe.service.impl;

import com.musicwebbe.model.*;
import com.musicwebbe.model.dto.CommentDTO;
import com.musicwebbe.model.dto.SongDTO;
import com.musicwebbe.model.dto.SongFavorite;
import com.musicwebbe.repository.*;
import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.repository.ISongRepository;
import com.musicwebbe.service.ISingerService;
import com.musicwebbe.service.ISingerSongService;
import com.musicwebbe.service.ISongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService implements ISongService {
    @Autowired
    ISingerSongService iSingerSongService;
    @Autowired
    ISingerService iSingerService;

    @Autowired
    ISongRepository iSongRepository;

    @Autowired
    ILikesRepository likesRepository;

    @Autowired
    ISingerRepository isingerRepository;

    @Autowired
    ISingerSongRepository iSingerSongRepository;

    @Autowired
    IAccountRepository iAccountRepository;

    @Autowired
    ILikesRepository iLikesRepository;

    @Autowired
    IPlaylistRepository iPlaylistRepository;

    @Autowired
    IPlaylistSongRepository iPlaylistSongRepository;

    @Autowired
    ICommentRepository iCommentRepository;


    @Override
    public Song save(Song song) {
        return iSongRepository.save(song);
    }


    @Override
    public Song edit(Song song) {
        return iSongRepository.save(song);
    }

    @Override
    public void delete(int id) {
        iSongRepository.deleteById(id);
    }


    @Override
    public Song findById(int id) {
        Optional<Song> optionalSong = iSongRepository.findById(id);
        if (optionalSong.isPresent()) {
            return optionalSong.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Song> getAll() {
        return iSongRepository.findAll();
    }

//    @Override
//    public List<Song> findTop5ByPlaysDesc() {
//        return iSongRepository.findTop5ByPlaysDesc();
//    }

    @Override
    public List<SongDTO> findTop5ByPlaysDesc(Account account) {
        List<Song>songList = iSongRepository.findTop5ByPlaysDesc();
        List<SongDTO> songDTOList = songList.stream()
                .map(song -> {
                    int isLiked = iLikesRepository.isLiked(song.getId(), account.getId());
                    return new SongDTO(song.getId(),song.getNameSong(),song.getImgSong(),song.getPathSong(),account.getId(),song.getDescription(),isLiked);
                }).collect(Collectors.toList());

        return songDTOList;
    }

    @Override
    public void deleteaSong(int id) {
        likesRepository.deleteAllBySong(iSongRepository.findById(id).get());
        iCommentRepository.deleteBySongId(id);
        iPlaylistSongRepository.deleteBySongId(id);
        iSingerSongRepository.deleteBySongId(id);
        iSongRepository.deleteById(id);

    }

    @Override
    public List<SongDTO2> getAllSong() {
        List<Song> songs = iSongRepository.findAll();
        return convertToSongDTOList(songs);
    }

    private List<SongDTO2> convertToSongDTOList(List<Song> songs) {
        return songs.stream()
                .map(song -> {
                    SongDTO2 songDTO2 = new SongDTO2();
                    songDTO2.setId(song.getId());
                    songDTO2.setNameSong(song.getNameSong());
                    songDTO2.setImgSong(song.getImgSong());
                    songDTO2.setPlays(song.getPlays());
                    songDTO2.setGenres(song.getGenres());
                    songDTO2.setTimeCreate(song.getTimeCreate());
                    songDTO2.setPathSong(song.getPathSong());
                    songDTO2.setDescription(song.getDescription());
                    songDTO2.setLikeQuantity(likesRepository.getLikeQuantity(song.getId()));
                    songDTO2.setNameSinger(song.getNameSinger());
                    return songDTO2;
                })
                .collect(Collectors.toList());
    }


    @Override
    public SongDTO2 getaSong(int id) {
        Song song = iSongRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm được bài hát"));
        int likeQuantity = likesRepository.getLikeQuantity(id);
        SongDTO2 songDTO2 = new SongDTO2(song, likeQuantity);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        if (!song.getComments().isEmpty()) {
            CommentDTO commentDTO = new CommentDTO();
            for (Comment comment : song.getComments()) {
                commentDTO.setAccountName(comment.getAccount().getName());
                commentDTO.setTimeComment(comment.getTimeComment());
                commentDTO.setContent(comment.getContent());
                commentDTOS.add(commentDTO);
            }
        }
        songDTO2.setComments(commentDTOS);
        return songDTO2;
    }

    @Override
    public SongDTO2 editaSong(SongDTO2 songDTO2) {
        Song existingSong = iSongRepository.findById(songDTO2.getId()).get();
        existingSong.setId(songDTO2.getId());
        existingSong.setNameSong(songDTO2.getNameSong());
        existingSong.setImgSong(songDTO2.getImgSong());
        existingSong.setPlays(songDTO2.getPlays());
        existingSong.setGenres(songDTO2.getGenres());
        existingSong.setTimeCreate(songDTO2.getTimeCreate());
        existingSong.setPathSong(songDTO2.getPathSong());
        existingSong.setDescription(songDTO2.getDescription());
        existingSong.setNameSinger(songDTO2.getNameSinger());
        Song savedSong = iSongRepository.save(existingSong);

        return new SongDTO2(savedSong, likesRepository.getLikeQuantity(songDTO2.getId()));
    }

    @Override
    public Song addSong(Account account, Song song) {
        song.setPlays(0);
        song.setTimeCreate(LocalDate.now());
        song.setAccount(account);
        iSongRepository.save(song);
        if (account.isAuth() == true) {
            Singer singer = iSingerService.findSingerByAccountID(account.getId());
            SingerSong singerSong = new SingerSong();
            singerSong.setSinger(singer);
            singerSong.setSong(song);
            iSingerSongService.save(singerSong);
        }
        return song;
    }

    public SongDTO findSongById(int id) {
        Optional<Song> optionalSong = iSongRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            SongDTO songDTO = new SongDTO();
            BeanUtils.copyProperties(song, songDTO);
            Account account = iAccountRepository.findById(song.getAccount().getId()).get();
            songDTO.setAccountName(account.getName());
            songDTO.setAccountID(account.getId());
            songDTO.setAuth(account.isAuth());
            return songDTO;
        } else {
            return null;
        }
    }

    @Override
    public List<SongDTO> getAllSongByGenresID(Song song) {
        int songGenresID = song.getGenres().getId();
        List<Song> songList = iSongRepository.findAllSongByGenresID(songGenresID);
        List<SongDTO> songDTOList = songList.stream()
                .map(aSong -> {
                    return new SongDTO(aSong.getId(), aSong.getNameSong(), aSong.getImgSong(), aSong.getPathSong(), aSong.getNameSinger());
                }).collect(Collectors.toList());
        return songDTOList;
    }

    @Override
    public List<SongDTO> findListSongByName(String name, Account account) {
        List<Song> songList = iSongRepository.findListSongByName(name);
        return songList.stream()
                .map(song -> {
                    int isLiked = iLikesRepository.isLiked(song.getId(), account.getId());
                    return new SongDTO(
                            song.getId(),
                            song.getNameSong(),
                            song.getImgSong(),
                            song.getPathSong(),
                            song.getAccount().getId(),
                            isLiked
                    );
                }).collect(Collectors.toList());
    }

    @Override
    public List<SongDTO> findListSongByNameSinger(String name, Account account) {
        List<Song> songList = iSongRepository.findListSongByNameSinger(name);
        return songList.stream().map(song -> {
            int isLiked = iLikesRepository.isLiked(song.getId(), account.getId());
            return new SongDTO(
                    song.getId(),
                    song.getNameSong(),
                    song.getImgSong(),
                    song.getPathSong(),
                    song.getAccount().getId(),
                    isLiked
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<List<SongDTO>> findListSongByPlaylist(String name, Account account) {
        List<Playlist> playlist = iPlaylistRepository.findAllPlaylistByNamePlaylist(name);
        List<List<SongDTO>> parentList = new ArrayList<>();
        for (Playlist playlist1 : playlist) {
            List<Song> songList = iSongRepository.findListSongByPlaylistName(playlist1.getNamePlaylist(), playlist1.getId());
            List<SongDTO> songDTOList;
            songDTOList = songList.stream()
                    .map(song -> {
                        int isLiked = iLikesRepository.isLiked(song.getId(), account.getId());
                        return new SongDTO(
                                song.getId(),
                                song.getNameSong(),
                                song.getImgSong(), song.getPathSong(),
                                song.getAccount().getId(),
                                playlist1.getPlaylistImg(),
                                playlist1.getNamePlaylist(), isLiked
                        );
                    }).collect(Collectors.toList());
            parentList.add(songDTOList);
        }
        return parentList;
    }
    public List<Song> getAllSongByAccountId(int id) {
        return iSongRepository.getAllByAccount_Id(id);
    }

    @Override
    public long getTotalSongs() {
        return iSongRepository.count();
    }


    @Override
    public List<SongFavorite> getAllFavoritesByUser(String username) {
        List<SongFavorite> listSongFavorite = new ArrayList<>();
        List<Likes> likesList = iLikesRepository.findAllByAccountEmail(username);
        for (Likes likes : likesList){
            SongFavorite songFavorite = new SongFavorite();
            BeanUtils.copyProperties(likes.getSong(), songFavorite);
            listSongFavorite.add(songFavorite);
        }
        return listSongFavorite;
    }

}
