package c.e.exper.mapper;

import c.e.exper.data.PlaceDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookMarkMapperTest {


    @Autowired
    BookMarkMapper bookMarkMapper;


    @Test
    void selectAllByUserBookMark() {

        List<PlaceDAO> test = bookMarkMapper.selectAllByUserBookMark("um");
        for (PlaceDAO placeDAO : test) {
            System.out.println(placeDAO);
        }
    }

    @Test
    void selectByUserBookMarkKeeper() {
        System.out.println(bookMarkMapper.selectByUserBookMarkKeeper("um"));
    }

    @Test
    void selectByUserBookMarkHotel() {
        System.out.println(bookMarkMapper.selectByUserBookMarkHotel("um"));
    }
}