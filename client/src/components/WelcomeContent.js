import * as React from 'react';
import PricesData from './data/PricesData';
import '../styles/App.css';

export default class WelcomeContent extends React.Component {

    render() {
        const token = localStorage.getItem("token");
        return (
            <div className="row justify-content-md-center">
                <div className="container">
                    {token ? (
                        <>
                            <div className="container">
                                <h1 className="display-4">Witaj</h1>
                                <p className="lead">Ta aplikacja pozwoli Ci dokonać analizy rynku nieruchomości. W zakładce <a href='http://localhost:3000/data' className='link'>Ceny mieszkań</a> możesz przefiltrować dane wedle własnych upodobań, pobrać je oraz wygenerować na ich podstawie wykresy.
                                </p>
                            </div>
                        </>
                    ) : (
                        <>
                            <div className="container">
                                <h1 className="display-4">Witaj</h1>
                                <p className="lead">Zaloguj się, aby obejrzeć zawartość strony</p>
                            </div>
                        </>
                    )}
                </div>

            </div>
        );
    }
}