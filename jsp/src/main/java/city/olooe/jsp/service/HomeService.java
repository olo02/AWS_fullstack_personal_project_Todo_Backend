package city.olooe.jsp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import city.olooe.jsp.mapper.HomeMapper;

@Service
public class HomeService {

    @Autowired
    private HomeMapper homeMapper;

    public String getTime() {
        return homeMapper.getTime();
    }

    public Object getList() {
        return homeMapper.getList();
    }

    public void create(Map<String, Object> map) {
        homeMapper.insert(map);
    }

    public void remove(Integer no) {
        homeMapper.remove(no);
    }
}
