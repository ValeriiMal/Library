(function(
    model
) {
    model.Address = Address;

    function Address({
        country,
        city,
        street,
        house,
    } = {}) {
        this.country = country || '';
        this.city = city || '';
        this.street = street || '';
        this.house = house || '';
    }

})(
    window.app.model = window.app.model || {}
    );
