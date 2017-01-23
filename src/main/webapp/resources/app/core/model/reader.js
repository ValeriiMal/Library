((model) => {
    model.Reader = Reader;

    function Reader({
        id,
        fName,
        mName,
        lName,
        phone,
        address,
        dateOfBirth,
    } = {}) {
        this.id = id || undefined;
        this.fName = fName || '';
        this.mName = mName || '';
        this.lName = lName || '';
        this.phone = phone || '';
        this.address = new model.Address(address);
        this.dateOfBirth = dateOfBirth || '';
    }

})(model = window.app.model || {});
