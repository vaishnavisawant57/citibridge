import React from "react";
import "./DataVisualization.css";
import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
  Title,
  LinearScale,
  CategoryScale,
} from "chart.js/auto";
import { Bar, Doughnut } from "react-chartjs-2";
import axios from "axios";

ChartJS.register(
  ArcElement,
  Tooltip,
  Tooltip,
  Title,
  Legend,
  LinearScale,
  CategoryScale
);

export const data1 = {
  labels: ["Valid Records", "Invalid Records"],
  datasets: [
    {
      label: "Valid vs Invalid Records",
      data: [0, 0],
      backgroundColor: ["#426995", "#857775"],
    },
  ],
};

export const data2 = {
  labels: [
    "Invalid Reference Number",
    "Invalid Transaction Date",
    "Invalid Payer Name",
    "Invalid Payer Account",
    "Invalid Payee Name",
    "Invalid Payee Account",
    "Invalid Amount",
  ],
  datasets: [
    {
      label: "Distribution of Invalid Records",
      data: [0, 0, 0, 0, 0, 0, 0],
      backgroundColor: [
        "#293649",
        "#184359",
        "#0e1720",
        "#426995",
        "#c5c5c1",
        "#1B0B9D",
        "#857775",
      ],
    },
  ],
};
const DataVisualization = () => {
  const [transactionCount, setTransactionCount] = React.useState([]);

  React.useEffect(() => {
    getTransactionCountPerReason();

    for (let i = 0; i < transactionCount.length - 2; i++) {
      data2.datasets[0].data[i] = transactionCount[i];
    }
    data1.datasets[0].data[0] = transactionCount[7];
    data1.datasets[0].data[1] = transactionCount[8];
  }, [transactionCount]);

  const getTransactionCountPerReason = async () => {
    try {
      const result = await axios.get(
        "http://localhost:8080/GetTransNumberPerReason"
      );
      setTransactionCount(result.data);
    } catch (error) {
      console.error("Error fetching transaction data:", error);
    }
  };
  return (
    <div className="vBase">
      <div className="chart-container">
        <Doughnut
          className="chart"
          data={data1}
          options={{
            plugins: {
              legend: {
                position: "top",
              },
              title: {
                display: true,
                text: "Valid vs Invalid Records",
                font: { size: 16 },
              },
            },
          }}
        />
      </div>
      <div className="chart-container">
        <Bar
          className="chart"
          data={data2}
          options={{
            plugins: {
              legend: {
                display: false,
              },
              title: {
                display: true,
                text: "Reasons for Invalid Transactions",
                font: { size: 16 },
              },
            },
            scales: {
              y: {
                beginAtZero: true,
              },
            },
          }}
        />
      </div>
    </div>
  );
};

export default DataVisualization;
