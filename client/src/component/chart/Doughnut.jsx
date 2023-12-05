import React, { useState } from 'react';

import { AgChartsReact } from 'ag-charts-react';

const Doughnut = () => {
   const [options, setOptions] = useState({
      data: getData(),
      title: {
         text: 'Attendence',
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
   return <AgChartsReact options={options} />;
};

const getData = () => {
   return [
      { asset: 'present', amount: 50000 },
      { asset: 'absent', amount: 30000 },

   ];
};

export default Doughnut;
