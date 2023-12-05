
import React, { useState } from "react";
import Calendar from "react-calendar";
import 'react-calendar/dist/Calendar.css';
import './calendar.css';

export default function CalendarC() {
  const [value, onChange] = useState(new Date());

  return (
    <div className="ds-cal">
      <Calendar
        onChange={onChange}
        value={value}
      />
    </div>
  );
}
