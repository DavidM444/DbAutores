package com.dbPostgresAutores.autores.model.market;

import com.dbPostgresAutores.autores.model.Film;
import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.manage.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory extends BaseUpdate {
    @OneToMany
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film filmId;
    @OneToMany
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store storeId;

    public Inventory(Film film, Store store) {
        super();
        this.filmId = film;
        this.storeId = store;
    }
}
