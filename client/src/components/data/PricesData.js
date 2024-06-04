import React, { useState, useEffect, useRef } from 'react';
import { request } from '../../axios_helper';
import FiltersForm from './FiltersForm';
import { exportToXML } from '../../helpers/saveToXML';
import { exportToJSON } from '../../helpers/saveToJSON';
import DataChart from './DataChart';
import '../../styles/App.css';

const PricesData = () => {
    const [pricesData, setPricesData] = useState([]);
    const [amountsData, setAmountsData] = useState([]);
    const [combinedData, setCombinedData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [filters, setFilters] = useState({
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

    const [filtersSubmitted, setFiltersSubmitted] = useState(false);
    const [showChartPrices, setShowChartPrices] = useState(false);
    const [showChartAmounts, setShowChartAmounts] = useState(false);
    const pricesChartRef = useRef(null);
    const amountsChartRef = useRef(null);
    const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));

    useEffect(() => {
        fetchData();
    }, []);

    useEffect(() => {
        combineData();
    }, [pricesData, amountsData]);

    const fetchData = () => {
        const queryParams = new URLSearchParams();
        Object.keys(filters).forEach(key => {
            if (filters[key]) {
                queryParams.append(key, filters[key]);
            }
        });

        setLoading(true);
        setError(null);

        Promise.all([
            request('GET', `/api/prices?${queryParams}`),
            request('GET', `/api/amounts?${queryParams}`)
        ]).then(([
            pricesResponse,
            amountsResponse
        ]) => {
            setPricesData(pricesResponse.data);
            setAmountsData(amountsResponse.data);
            setLoading(false);
        }).catch(error => {
            console.log('Error:', error);
            setError(error.message);
            setLoading(false);
        });
    };

    const combineData = () => {
        if (filters.sortNumber === 1) {
            const combined = amountsData.map(numberItem => {
                const matchingNumberItem = pricesData.find(priceItem => priceItem.id === numberItem.id);
                return {
                    ...numberItem,
                    price: matchingNumberItem ? matchingNumberItem.price : 'N/A'
                };
            });
            setCombinedData(combined);

        }
        else {
            const combined = pricesData.map(priceItem => {
                const matchingNumberItem = amountsData.find(amountItem => amountItem.id === priceItem.id);
                return {
                    ...priceItem,
                    amount: matchingNumberItem ? matchingNumberItem.amount : 'N/A'
                };
            });
            setCombinedData(combined);

        }
    };

    const handleExportToXML = () => {
        const xmlData = combinedData.map(item => (
            `<item>
                <region>${item.region}</region>
                <quarter>${item.quarter}</quarter>
                <type>${item.type}</type>
                <surface>${item.surface}</surface>
                <year>${item.year}</year>
                <price>${item.price}</price>
                <amount>${item.amount}</amount>
            </item>`
        )).join('');

        exportToXML(`<data>${xmlData}</data>`, 'filtered_data');
    };

    const handleExportToJSON = () => {
        exportToJSON(combinedData, 'filtered_data');
    };

    const handleDownloadChart = () => {
        if (pricesChartRef.current) {
            pricesChartRef.current.downloadChart();
        }
        if (amountsChartRef.current) {
            amountsChartRef.current.downloadChart();
        }
    };

    const handleFilterReset = () => {
        setFiltersSubmitted(false);
        setShowChartAmounts(false);
        setShowChartPrices(false);
    };

    const hasFilters = () => {
        return Object.values(filters).some(value => value !== '');
    };


    if (loading) {
        return <div>Ładowanie danych...</div>;
    }

    if (!isLoggedIn) {
        return <div className='alert alert-danger'>Proszę się zalogować, aby zobaczyć dane.</div>;
    }

    if (error) {
        return <div>Błąd: {error}</div>;
    }

    return (
        <div>
            <FiltersForm
                filters={filters}
                setFilters={setFilters}
                onFilterSubmit={() => setFiltersSubmitted(true)}
                fetchData={fetchData}
                onFilterReset={handleFilterReset}
            />
            <div className="form-container">
                <p className="lead">Eksport danych</p>
                <div className="row justify-content-start">
                    <div className="col-6">
                        <button className="submit-button full-width" onClick={handleExportToXML}>Pobierz dane w formacie XML</button>
                    </div>
                    <div className="col-6">
                        <button className="submit-button full-width" onClick={handleExportToJSON}>Pobierz dane w formacie JSON</button>
                    </div>
                </div>
                <p></p>
                {filtersSubmitted && hasFilters() && (
                    <>
                        <div className="row justify-content-start">
                            <div className="col-6">
                                <button className="submit-button violet-btn full-width" onClick={() => setShowChartPrices(!showChartPrices)}>
                                    {showChartPrices ? 'Ukryj wykres cen' : 'Pokaż wykres cen'}
                                </button>
                            </div>
                            {showChartPrices && (
                                <div className="col-6">
                                    <button className="submit-button violet-btn full-width" onClick={handleDownloadChart}>
                                        Pobierz wykres cen
                                    </button>
                                </div>
                            )}
                        </div>
                        <p></p>
                        <div className="row justify-content-start">
                            <div className="col-6">
                                <button className="submit-button violet-btn full-width" onClick={() => setShowChartAmounts(!showChartAmounts)}>
                                    {showChartAmounts ? 'Ukryj wykres liczby oddanych mieszkań' : 'Pokaż wykres liczby oddanych mieszkań'}
                                </button>
                            </div>


                            {showChartAmounts && (
                                <div className="col-6">
                                    <button className="submit-button violet-btn full-width" onClick={handleDownloadChart}>
                                        Pobierz wykres liczby oddanych mieszkań
                                    </button>
                                </div>
                            )}
                        </div>
                    </>
                )}
            </div>
            <div className="container">
                {showChartPrices && (
                    <>
                        <DataChart data={pricesData} yLabel="Średnia cena sprzedanych mieszkań (zł)" downloadFileName="prices_chart" ref={amountsChartRef} />
                    </>
                )}
                {showChartAmounts && (
                    <>
                        <DataChart data={amountsData} yLabel="Liczba oddanych mieszkań" downloadFileName="amounts_chart" ref={amountsChartRef} />
                    </>
                )}
                <table className="table">
                    <thead>
                        <tr>
                            <th>Region</th>
                            <th>Rok</th>
                            <th>Kwartał</th>
                            <th>Typ mieszkania</th>
                            <th>Powierzchnia</th>
                            <th>Średnia cena (zł)</th>
                            <th>Liczba oddanych mieszkań</th>
                        </tr>
                    </thead>
                    <tbody>
                        {combinedData.map(item => (
                            <tr key={item.id}>
                                <td>{item.region}</td>
                                <td>{item.year}</td>
                                <td>{item.quarter}</td>
                                <td>{item.type}</td>
                                <td>{item.surface}</td>
                                <td>{item.price}</td>
                                <td>{item.amount}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default PricesData;
