package c.e.exper.service;

import c.e.exper.data.PlaceDAO;
import c.e.exper.mapper.PlaceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceService {
    static String[] hotelCode = {"B02010100", "B02010200", "B02010300", "B02010400", "B02010500", "B02010600", "B02011500"};
    static String[] motelCode = {"B02010900"};
    static String[] pensionCode = {"B02010700"};
    static String[] homeCode = {"B02011100"};
    final
    PlaceMapper placeMapper;

    public PlaceService(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }

    public List<PlaceDAO> 장소_조회(String areaCode, String cat1) {
        return placeMapper.selectByPlace(areaCode, cat1);
    }

    public List<PlaceDAO> 장소_조회2(String areaCode, String contenttypeid) {
        return placeMapper.selectByPlace2(areaCode, contenttypeid);
    }

    public List<PlaceDAO> 장소_검색(String areaCode, String cat1, String keyword) {
        return placeMapper.keywordByPlace(areaCode, cat1, keyword);
    }

    public List<PlaceDAO> 장소_검색Tour(String areaCode, String contenttypeid, String keyword) {
        return placeMapper.keywordByPlaceTour(areaCode, contenttypeid, keyword);
    }

    public List<PlaceDAO> searchByKeyWord(String keyWord) {
        return placeMapper.SearchPlaceByKeyword(keyWord);
    }

    public List<PlaceDAO> 카테고리별_조회(String category) {
        List<PlaceDAO> categoryPlace = new ArrayList<>();

        switch (category) {
            case "hotel" -> {
                for (String hotel : hotelCode) {
                    categoryPlace.addAll(placeMapper.findByCategory(hotel));
                }

            }
            case "motel" -> {
                for (String motel : motelCode) {
                    categoryPlace.addAll(placeMapper.findByCategory(motel));
                }
            }

            case "pension" -> {
                for (String pension : pensionCode) {
                    categoryPlace.addAll(placeMapper.findByCategory(pension));
                }
            }

            case "home" -> {
                for (String home : homeCode) {
                    categoryPlace.addAll(placeMapper.findByCategory(home));
                }
            }
        }

        return categoryPlace;
    }

    public List<PlaceDAO> 카테고리_지역_조회(String category, String areaCode) {

        List<PlaceDAO> categoryArea = new ArrayList<>();

        switch (category) {
            case "hotel" -> {
                for (String hotel : hotelCode) {
                    categoryArea.addAll(placeMapper.CategoryAreaCode(hotel, areaCode));
                }
            }
            case "motel" -> {
                for (String motel : motelCode) {
                    categoryArea.addAll(placeMapper.CategoryAreaCode(motel, areaCode));
                }

            }
        }
        return categoryArea;
    }

    public List<PlaceDAO> 카테고리_옵션_조회(String category, String option) {

        List<PlaceDAO> categoryOption = new ArrayList<>();

        switch (category) {
            case "hotel" -> {
                for (String hotel : hotelCode) {
                    categoryOption.addAll(placeMapper.findByOption(hotel, option));
                }
            }
            case "motel" -> {
                for (String motel : motelCode) {
                    categoryOption.addAll(placeMapper.findByOption(motel, option));
                }
            }
        }
        return categoryOption;
    }

    //장소등록
    public void placeAdd(PlaceDAO placeAdd) {
        placeMapper.placeAdd(placeAdd);
    }

    public List<PlaceDAO> 키보드_지역_조회(String category, String keyword) {

        List<PlaceDAO> keywordArea = new ArrayList<>();

        switch (category) {
            case "hotel" -> {
                for (String hotel : hotelCode) {
                    System.out.println("hotel = " + hotel);
                    keywordArea.addAll(placeMapper.getKeyword(hotel, keyword));
                }
            }
            case "motel" -> {
                for (String motel : motelCode) {
                    keywordArea.addAll(placeMapper.getKeyword(motel, keyword));
                }
            }
        }
        return keywordArea;
    }
}
//   public List<PlaceDAO> 지역_조회(){
//      return placeMapper.selectByPlaceTo(int areaCode);
//   }
