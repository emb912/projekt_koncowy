import React, { useEffect, useState, forwardRef, useImperativeHandle } from 'react';
import Chart from 'chart.js/auto';

const DataChart = forwardRef(({ data, yLabel, downloadFileName }, ref) => {
    const [chartInst, setChartInstance] = useState(null);

    useImperativeHandle(ref, () => ({
        downloadChart() {
            if (chartInst) {
                const link = document.createElement('a');
                link.href = chartInst.toBase64Image();
                link.download = `${downloadFileName}.png`;
                link.click();
            }
        }
    }));

    useEffect(() => {
        if (data.length > 0) {
            const chartData = {};
            const keys = [...new Set(data.map(item => `${item.region} ${item.type} ${item.surface}`))];
            const colors = generateRandomColors(keys.length);

            data.forEach(item => {
                const key = `${item.region}: ${item.type}, ${item.surface}`;
                if (!chartData[key]) {
                    chartData[key] = [];
                }
                chartData[key].push({ x: `${item.year} Q${item.quarter}`, y: item.price || item.amount, surface: item.surface, region: item.region });
            });

            const ctx = document.getElementById(downloadFileName).getContext('2d');
            const existingChart = Chart.getChart(ctx);
            if (existingChart) {
                existingChart.destroy();
            }

            const datasets = Object.keys(chartData).map((key, index) => {
                const color = colors[index];
                return {
                    label: key,
                    data: chartData[key],
                    backgroundColor: color,
                    borderColor: color.replace('0.5', '1'),
                    borderWidth: 1,
                    pointRadius: 5,
                    pointHoverRadius: 8,
                    showLine: true
                };
            });

            const chartInst = new Chart(ctx, {
                type: 'line',
                data: {
                    datasets: datasets
                },
                options: {
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Rok i kwartał'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: yLabel
                            },
                        }
                    },
                    plugins: {
                        tooltip: {
                            callbacks: {
                                label: function (context) {
                                    const dataPoint = context.raw;
                                    return `${dataPoint.region} (powierzchnia: ${dataPoint.surface} m²) - ${dataPoint.y}`;
                                }
                            }
                        }
                    }

                }
            });
            setChartInstance(chartInst);
        }
    }, [data]);

    const generateRandomColors = (numColors) => {
        const colors = [];
        for (let i = 0; i < numColors; i++) {
            const color = `rgba(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, 0.5)`;
            colors.push(color);
        }
        return colors;
    };

    return (
        <div>
            <canvas id={downloadFileName} width="400" height="260"></canvas>
        </div>
    );
});

export default DataChart;
