package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

public interface IRoomService {
    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws SQLException, IOException;
=======
public interface IRoomService {
    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SQLException;
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8

    List<String> getAllRoomTypes();

    List<Room> getAllRooms();

<<<<<<< HEAD
    byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException;

    void deleteRoom(Long roomId);
=======
    byte[] getRoomPhotoByRoomId(Long id) throws SQLException;
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8

    Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes);

    Optional<Room> getRoomById(Long roomId);

    List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);
}
