package com.example.lowseven.tourguideapp;

//title, image, location
enum DominicanRepublic implements ILocations {
    PICO_DUARTE(R.string.duarte_title, R.drawable.dr_pico_duarte, R.string.duarte_coordinates),
    BAVARO(R.string.bavaro_title, R.drawable.dr_bavaro, R.string.bavaro_coordinates),
    MACAO(R.string.macao_title, R.drawable.dr_macao_beach, R.string.macao_coordinates),
    PLAZA_DUARTE(R.string.plaza_title, R.drawable.dr_plaza_espanola, R.string.plaza_coordinates);

    private final int title;
    private final int image;
    private final int coordinates;

    DominicanRepublic(int title, int image, int coordinates) {
        this.title = title;
        this.image = image;
        this.coordinates = coordinates;
    }

    @Override
    public int title() {
        return this.title;
    }

    @Override
    public int image() {
        return this.image;
    }

    @Override
    public int coordinates() {
        return this.coordinates;
    }

}

enum Italy implements ILocations {
    COLOSSEO(R.string.colosseo_title, R.drawable.it_colosseo , R.string.colosseo_coordinates),
    GRANDE_CANALE(R.string.grande_canale_title, R.drawable.it_grande_canale, R.string.grande_canale_coordinates),
    LE_ALPI(R.string.le_alpi_title, R.drawable.it_le_alpi, R.string.le_alpi_coordinates),
    PIAZZA_SAN_MARCO(R.string.piazza_san_marco_title, R.drawable.it_piazza_san_marco, R.string.piazza_san_marco_coordinates);

    private final int title;
    private final int image;
    private final int coordinates;

    Italy(int title, int image, int coordinates) {
        this.title = title;
        this.image = image;
        this.coordinates = coordinates;
    }

    @Override
    public int title() {
        return this.title;
    }

    @Override
    public int image() {
        return this.image;
    }

    @Override
    public int coordinates() {
        return this.coordinates;
    }

}

enum Spain implements ILocations {
    CATEDRAL_BARCELONA(R.string.catedral_barcelona_title, R.drawable.es_catedral_barcelona , R.string.catedral_barcelona_coordinates),
    CATEDRAL_TOLEDO(R.string.catedral_toledo_title, R.drawable.es_catedral_toledo, R.string.catedral_toledo_coordinates),
    IBIZA(R.string.ibiza_title, R.drawable.es_ibiza, R.string.ibiza_coordinates),
    TEMPLO_DE_DEBOD(R.string.templo_de_debod_title, R.drawable.es_templo_de_debod, R.string.templo_de_debod_coordinates);

    private final int title;
    private final int image;
    private final int coordinates;

    Spain(int title, int image, int coordinates) {
        this.title = title;
        this.image = image;
        this.coordinates = coordinates;
    }

    @Override
    public int title() {
        return this.title;
    }

    @Override
    public int image() {
        return this.image;
    }

    @Override
    public int coordinates() {
        return this.coordinates;
    }

}

enum Japan implements ILocations {
    FUJI_MOUNTAIN(R.string.fuji_title, R.drawable.jp_fuji_montain, R.string.fuji_coordinates),
    ITSUKUSHIMA(R.string.itsukushima_title, R.drawable.jp_itsukushima, R.string.itsukushima_coordinates),
    ROPPONGI(R.string.roppongi_title, R.drawable.jp_roppongi, R.string.roppongi_coordinates),
    TOKYO_TOWER(R.string.tokyo_tower_title, R.drawable.jp_tokyo_tower, R.string.tokyo_tower_coordinates);

    private final int title;
    private final int image;
    private final int coordinates;

    Japan(int title, int image, int coordinates) {
        this.title = title;
        this.image = image;
        this.coordinates = coordinates;
    }

    @Override
    public int title() {
        return this.title;
    }

    @Override
    public int image() {
        return this.image;
    }

    @Override
    public int coordinates() {
        return this.coordinates;
    }

}
