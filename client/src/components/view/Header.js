import * as React from 'react';


export default function Header(props) {
    return (
        <header className="App-header">
            <a href="http://localhost:3000" className="App-link">
                <h1 className="App-title">{props.pageTitle}</h1>
            </a>
        </header>
    )
}