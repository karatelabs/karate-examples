package io.karatelabs.examples.database;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dogs")
public class DogsController {

    @Autowired(required = true)
    private JdbcTemplate jdbc;

    private final AtomicInteger counter = new AtomicInteger();
    
    private static final RowMapper<Dog> ROW_MAPPER = (rs, rowNum) -> new Dog(rs.getInt("ID"), rs.getString("NAME"));

    @PostMapping
    public Dog create(@RequestBody Dog dog) {
        int id = counter.incrementAndGet();
        dog.setId(id);
        jdbc.update("INSERT INTO DOGS(ID, NAME) values(?, ?)", dog.getId(), dog.getName());
        return dog;
    }

    @GetMapping
    public Collection<Dog> list() {
        return jdbc.query("SELECT * FROM DOGS", ROW_MAPPER);
    }
    
    @GetMapping("/{id:.+}")
    public Dog get(@PathVariable int id) {
        return jdbc.queryForObject("SELECT * FROM DOGS D WHERE D.ID = ?", ROW_MAPPER, id);
    }

}
