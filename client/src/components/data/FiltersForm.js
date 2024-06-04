import React, { useState } from 'react';
import '../../styles/FiltersForm.css';

const FiltersForm = ({
    filters,
    setFilters,
    onFilterSubmit,
    fetchData,
    onFilterReset
}) => {
    const wojewodztwa = [
        'DOLNOŚLĄSKIE', 'KUJAWSKO-POMORSKIE', 'LUBELSKIE', 'LUBUSKIE',
        'ŁÓDZKIE', 'MAŁOPOLSKIE', 'MAZOWIECKIE', 'OPOLSKIE',
        'PODKARPACKIE', 'PODLASKIE', 'POMORSKIE', 'ŚLĄSKIE',
        'ŚWIĘTOKRZYSKIE', 'WARMIŃSKO-MAZURSKIE', 'WIELKOPOLSKIE', 'ZACHODNIOPOMORSKIE'
    ];

    const lata = Array.from({ length: 2022 - 2012 + 1 }, (_, i) => 2012 + i);

    const powierzchnie = [
        'do 40 m2', 'od 40,1 do 60 m2', 'od 60,1 do 80 m2', 'od 80,1 m2'
    ];

    const [error, setError] = useState("");

    const handleFilterChange = (event) => {
        const { name, value } = event.target;
        setFilters({ ...filters, [name]: value });
    };

    const handleSortRadioChange = (event) => {
        const { name, value } = event.target;
        setFilters({ ...filters, [name]: value, sortPrice: '0', sortAmount: '0', sortDefault: '0', [name]: value });
    };

    const handleRadioChange = (event) => {
        const { name, value } = event.target;
        setFilters({ ...filters, [name]: value, before: '0', after: '0', oneYear: '0', [name]: value });
    };

    const handleRadioClick = (event) => {
        const { name } = event.target;
        if (filters[name]) {
            setFilters({ ...filters, [name]: '' });
        }
    };

    const handleFilterSubmit = (event) => {
        event.preventDefault();

        const noFiltersSet = Object.values(filters).every(value => value === '' || value === '0');

        if (noFiltersSet) {
            setError("Proszę wybrać co najmniej jeden filtr/sposób sortowania");
        } else {
            setError("");
            fetchData();
            onFilterSubmit();
        }
        console.log(filters)
    };

    const handleFilterReset = () => {
        setFilters({
            year: '',
            region: '',
            surface: '',
            before: '',
            after: '',
            sortPrice: '',
            sortAmount: '',
            sortDefault: '',
            oneYear: ''
        });
        onFilterReset();
        setError("");
    };


    return (
        <div className="form-container my-3">
            <p className="lead">Filtrowanie danych</p>
            {error && <p className="alert alert-danger">{error}</p>}

            <form onSubmit={handleFilterSubmit} className="filters-form">
                <div className="form-group">
                    <label className="inline-label">
                        <input
                            type="radio"
                            name="sort"
                            checked={filters.sortPrice === '1'}
                            value="1"
                            onChange={(event) => handleSortRadioChange({ target: { name: 'sortPrice', value: event.target.checked ? '1' : '0' } })}
                            onClick={handleRadioClick}
                        />
                        Sortowanie po cenie rosnąco
                    </label>
                    <label className="inline-label">
                        <input
                            type="radio"
                            name="sort"
                            checked={filters.sortAmount === '1'}
                            value="1"
                            onChange={(event) => handleSortRadioChange({ target: { name: 'sortAmount', value: event.target.checked ? '1' : '0' } })}
                            onClick={handleRadioClick}
                        />
                        Sortowanie po liczbie oddanych mieszkań rosnąco
                    </label>
                    <label className="inline-label">
                        <input
                            type="radio"
                            name="sort"
                            checked={filters.sortDefault === '1'}
                            value="1"
                            onChange={(event) => handleSortRadioChange({ target: { name: 'sortDefault', value: event.target.checked ? '1' : '0' } })}
                            onClick={handleRadioClick}
                        />
                        Sortowanie domyślne
                    </label>
                </div>


                <div className="form-group">
                    <label>
                        Wybierz województwo:
                        <select name="region" value={filters.region} onChange={handleFilterChange}>
                            <option value="">Wszystkie</option>
                            {wojewodztwa.map(woj => (
                                <option key={woj} value={woj}>{woj}</option>
                            ))}
                        </select>
                    </label>
                </div>

                <div className="form-group">
                    <label>
                        Wybierz powierzchnię mieszkania:
                        <select name="surface" value={filters.surface} onChange={handleFilterChange}>
                            <option value="">Wszystkie</option>
                            {powierzchnie.map(sfc => (
                                <option key={sfc} value={sfc}>{sfc}</option>
                            ))}
                        </select>
                    </label>
                </div>

                <fieldset className="form-group">
                    Wybierz dane według roku:
                    <label className="inline-label">
                        <input
                            type="radio"
                            name="yearFilter"
                            checked={filters.before === '1'}
                            value="1"
                            onChange={(event) => handleRadioChange({ target: { name: 'before', value: event.target.checked ? '1' : '0' } })}
                            onClick={handleRadioClick}
                        />
                        Dane do roku
                    </label>
                    <label className="inline-label">
                        <input
                            type="radio"
                            name="yearFilter"
                            checked={filters.after === '1'}
                            value="1"
                            onChange={(event) => handleRadioChange({ target: { name: 'after', value: event.target.checked ? '1' : '0' } })}
                            onClick={handleRadioClick}
                        />
                        Dane od roku
                    </label>
                    <label className="inline-label">
                        <input
                            type="radio"
                            name="yearFilter"
                            checked={filters.oneYear === '1'}
                            value="1"
                            onChange={(event) => handleRadioChange({ target: { name: 'oneYear', value: event.target.checked ? '1' : '0' } })}
                            onClick={handleRadioClick}
                        />
                        Dane z roku
                    </label>
                </fieldset>

                <div className="form-group">
                    <label>
                        Wybierz rok:
                        <select name="year" value={filters.year} onChange={handleFilterChange}>
                            <option value="">Wszystkie</option>
                            {lata.map(rok => (
                                <option key={rok} value={rok}>{rok}</option>
                            ))}
                        </select>
                    </label>
                </div>

                <div className="row justify-content-start">
                    <div className="col-6">
                        <button type="submit" className="submit-button">Filtruj</button>
                    </div>
                    <div className="col-6">
                        <button type="reset" className="submit-button" onClick={handleFilterReset}>Wyczyść filtry</button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default FiltersForm;
