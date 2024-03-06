import React, { useEffect, useState } from 'react';

import { AgChartsReact } from 'ag-charts-react';

const Doughnut = ({ data }) => {

   const [options, setOptions] = useState({
      title: {
         text: 'Attendance',
      },
      series: [
         {
            type: 'pie',
            angleKey: 'amount',
            legendItemKey: 'asset',
         },
      ],
      overlays: {
         noData: {
            text: 'No data to display',
         },
      },
   });

   useEffect(() => {
      let totalPresent = 0;
      let totalAbsent = 0;

      data.forEach(item => {
         totalPresent += item.present;
         totalAbsent += item.absent;
      });

      const newOptions = {
         ...options,
         data: [
            { asset: 'present', amount: totalPresent },
            { asset: 'absent', amount: totalAbsent },
         ],
      };

      setOptions(newOptions);
   }, [data]);


   return <AgChartsReact options={options} />;
};



export default Doughnut;
