import React, { useState } from 'react';
import Header from '../component/header/Header';
import './exam.css';
const Exam = () => {
   return (
      <div className='exam'>
         <Header data={"Exam"} />
         <div className='exam-panel'>
            <div className='ep-list'>
               <p className='p'>Exam List</p>
               <div className='exam-list-container'>


                  {Array.from({ length: 4 }).map((_, index) => (
                     <ExamList key={index} />
                  ))}


               </div>
            </div>
            <div className='ep-form'>
               <ExamForm />
            </div>
         </div>
      </div>
   )
}


const ExamList = () => {
   return (
      <div className='exam-list-item active-exam'>
         <p >Sessional 1/5 (sem 7)</p>
      </div>
   )
}

const ExamForm = () => {
   return (
      <div style={{ width: "100%", height: "100%" }}>
         <p className='ex-f-n'>Exam form</p>
         <ExamProcess />
      </div>
   )
}
const ExamProcess = () => {

   const setps = (data) => {
      switch (data) {
         case 'A':
            return <ExamStepA />
         case 'B':
            return <ExamStepB />
         case 'C':
            return <ExamStepC />
         case 'D':
            return <ExamStepD />
         case 'E':
            return <ExamStepE />
         case 'F':
            return <ExamStepF />
         case 'G':
            return <ExamStepG />
         default:
            break;
      }
   }

   //----------------
   const [step, setStep] = useState('A')
   const [status, setStatus] = useState(1)

   const changeSection = (data) => {
      console.log(data)
      setStep(data)
      switch (data) {
         case 'A':
            setStatus(1)
            break;
         case 'B':
            setStatus(2)
            break;
         case 'C':
            setStatus(3)
            break;
         case 'D':
            setStatus(4)
            break;
         case 'E':
            setStatus(5)
            break;
         case 'F':
            setStatus(6)
            break;
         case 'G':
            setStatus(7)
            break;
         default:
            break;

      }
   }

   //--------
   return (
      <>
         <div style={{ width: "100%", height: 'auto' }}>
            <ExamStatus status={status} changeSection={changeSection} />
         </div>
         {
            setps(step)
         }

      </>

   )
}

const ExamStatus = ({ status, changeSection }) => {



   return (
      <div className='exam-status'>

         <div className='es-1'>

            <div className='status active2' onClick={() => changeSection('A')}>
               Start
            </div>

            <div className={`status ${status >= 1 ? (status !== 1 ? 'active2' : 'active') : ''}`} onClick={() => changeSection('A')}>
               Fill Form
            </div>

            <div className={`status ${status >= 2 ? (status !== 2 ? 'active2' : 'active') : ''}`} onClick={() => changeSection('B')}>
               Payment
            </div>
            <div className={`status ${status >= 3 ? (status !== 3 ? 'active2' : 'active') : ''}`} onClick={() => changeSection('C')}>
               Correction
            </div>
            <div className={`status ${status >= 4 ? (status !== 4 ? 'active2' : 'active') : ''}`} onClick={() => changeSection('D')}>
               Verification
            </div>
            <div className={`status ${status >= 5 ? (status !== 5 ? 'active2' : 'active') : ''}`} onClick={() => changeSection('E')}>
               Schedule
            </div>
            <div className={`status ${status >= 6 ? (status !== 6 ? 'active2' : 'active') : ''}`} onClick={() => changeSection('F')}>
               Result
            </div>
            <div className={`status ${status >= 7 ? (status !== 7 ? 'active2' : 'active') : ''}`} onClick={() => changeSection('G')}>
               Recheck
            </div>

         </div>

      </div>
   )
}


////////////// =--------------- AAAAAAAAAAAAAAAAAAA
const ExamStepA = () => {

   const initialCheckboxValues = {
      checkbox1: false,
      checkbox2: false,
      checkbox3: false,
      checkbox4: false,
      checkbox5: false,
      checkbox6: false,
      checkbox7: false,
      checkbox8: false,
      checkbox9: false,
      checkbox10: false,
      checkbox11: false,
      checkbox12: false,
      checkbox13: false,
      checkbox14: false,
      checkbox15: false,
      checkbox16: false,
      checkbox17: false,
      checkbox18: false,
      checkbox19: false,
      checkbox20: false,
      checkbox21: false,
   };

   const [checkboxValues, setCheckboxValues] = useState(initialCheckboxValues);

   const handleCheckboxChange = (checkboxName) => {
      setCheckboxValues((prevValues) => ({
         ...prevValues,
         [checkboxName]: !prevValues[checkboxName],
      }));
   };

   const submitHandle = () => {
      const trueCheckboxValues = Object.entries(checkboxValues).reduce(
         (acc, [key, value]) => {
            if (value === true) {
               acc[key] = value;
            }
            return acc;
         },
         {}
      );
      console.log(trueCheckboxValues)
   };


   return (
      <div className='exam-steps'>
         <div className='ex-sa-1'>
            <table >
               <tbody>

                  <tr>
                     <td>B. Tech CSE (AI & ML)</td>
                     <td>Sem 7th</td>
                  </tr>
                  <tr>
                     <td>Amrendra Yadav</td>
                     <td>2004282530002</td>
                  </tr>
               </tbody>
            </table>
         </div>
         <div className='ex-sa-2'>
            <div className='ex-sa-list'>
               <p className='ex-sa-n'>Subject List</p>
               <div className="ex-sa-list-c1" >
                  {Object.keys(checkboxValues).map((checkboxName) => (
                     <label className='exam-sub-li-1' key={checkboxName}>
                        <input
                           type="checkbox"
                           checked={checkboxValues[checkboxName]}
                           onChange={() => handleCheckboxChange(checkboxName)}
                        />
                        <span >{` ${checkboxName}`}</span>
                     </label>
                  ))}
               </div>
            </div>
            <div className='ex-sa-list-view'>
               <div className='ex-sa-list-view-1'>
                  <p className='ex-sa-n'>Selected Subject</p>
                  <div className='ex-sa-list-view-1-1'>
                     {
                        Object.entries(checkboxValues).map(([key, value], index) => {

                           if (value === true) {
                              return (<ListA key={index} data={[key, value]} handleCheckboxChange={handleCheckboxChange} />)
                           }
                           else {
                              return null
                           }
                        })
                     }

                  </div>
               </div>
               <button onClick={submitHandle}>Submit</button>
            </div>
         </div>

      </div>
   )
}

const ListA = ({ data, handleCheckboxChange }) => {
   // console.log(data);
   const [key, value] = data;

   return (
      <label className='exam-sub-li-1 esl-1-2' key={key}>
         <span>{` ${key}: ${value}`}</span>
         <p onClick={() => handleCheckboxChange(key)}>

         </p>
      </label>
   );
};


////////////// -------------------BBBBBBBBBBBBBBBBBBBB
const ExamStepB = () => {
   return (
      <div className='exam-steps'>
         <div className='ex-sa-1'>
            <table >
               <thead></thead>
               <tbody>

                  <tr>
                     <td>B. Tech CSE (AI & ML)</td>
                     <td>Sem 7th</td>
                     <td>Total sub 7</td>
                     <td>Ref id: 12342379</td>
                  </tr>
                  <tr>
                     <td>Amrendra Yadav</td>
                     <td>2004282530002</td>
                     <td>Put</td>
                     <td>$2000</td>
                  </tr>
               </tbody>
            </table>
         </div>
         <div className='ex-sa-2'>
            <div className='ex-sb-opt'>
               <p className='ex-sa-n'>Payment Options</p>
               <div className="ex-sb-py-list" >

                  <div className='ex-sb-py-o1'>
                     <p>
                        BHIM UPI
                     </p>
                  </div>
                  <div className='ex-sb-py-o1'>
                     <p>
                        Net Banking
                     </p>
                  </div>
                  <div className='ex-sb-py-o1'>
                     <p>
                        Credit Card
                     </p>
                  </div>
                  <div className='ex-sb-py-o1'>
                     <p>
                        Debit Card
                     </p>
                  </div>
                  <div className='ex-sb-py-o1'>
                     <p>
                        PayTm
                     </p>
                  </div>
                  <div className='ex-sb-py-o1'>
                     <p>
                        Google  Pay
                     </p>
                  </div>
                  <div className='ex-sb-py-o1'>
                     <p>
                        Phone Pay
                     </p>
                  </div>
                  <div className='ex-sb-py-o1'>
                     <p>
                        NEFT
                     </p>
                  </div>

               </div>
            </div>
         </div>

      </div>
   )
}

///////////////////-------------------CCCCCCCCCCCCCCCC


const ExamStepC = () => {

   const initialCheckboxValues = {
      checkbox1: false,
      checkbox2: false,
      checkbox3: false,
      checkbox4: false,
      checkbox5: false,
      checkbox6: false,
      checkbox7: false,
      checkbox8: false,
      checkbox9: false,
      checkbox10: false,
      checkbox11: false,
      checkbox12: false,
   };

   const [checkboxValues, setCheckboxValues] = useState(initialCheckboxValues);

   const handleCheckboxChange = (checkboxName) => {
      setCheckboxValues((prevValues) => ({
         ...prevValues,
         [checkboxName]: !prevValues[checkboxName],
      }));
   };

   const submitHandle = () => {
      const trueCheckboxValues = Object.entries(checkboxValues).reduce(
         (acc, [key, value]) => {
            if (value === true) {
               acc[key] = value;
            }
            return acc;
         },
         {}
      );
      console.log(trueCheckboxValues)
   };


   return (
      <div className='exam-steps'>
         <div className='ex-sa-1'>
            <table >
               <tr>
                  <td>B. Tech CSE (AI & ML)</td>
                  <td>Sem 7th</td>
                  <td>Last Date: 23/01/1024</td>
               </tr>
               <tr>
                  <td>Amrendra Yadav</td>
                  <td>2004282530002</td>
                  <td>Exam form no: 4655436546</td>
               </tr>
            </table>
         </div>
         <div className='ex-sa-2'>
            <div className='ex-sa-list'>
               <p className='ex-sa-n'>Subject List</p>
               <div className="ex-sa-list-c1" >
                  {Object.keys(checkboxValues).map((checkboxName) => (
                     <label className='exam-sub-li-1' key={checkboxName}>
                        <input
                           type="checkbox"
                           checked={checkboxValues[checkboxName]}
                           onChange={() => handleCheckboxChange(checkboxName)}
                        />
                        <span >{` ${checkboxName}`}</span>
                     </label>
                  ))}
               </div>
            </div>
            <div className='ex-sa-list-view'>
               <div className='ex-sa-list-view-1'>
                  <p className='ex-sa-n'>Selected Subject</p>
                  <div className='ex-sa-list-view-1-1'>
                     {
                        Object.entries(checkboxValues).map(([key, value], index) => {

                           if (value === true) {
                              return (<ListA key={index} data={[key, value]} handleCheckboxChange={handleCheckboxChange} />)
                           }
                           else {
                              return null
                           }
                        })
                     }

                  </div>
               </div>
               <button onClick={submitHandle}>Submit</button>
            </div>
         </div>

      </div>
   )
}


////////////////-------------------DDDDDDDDDDDDDDDDDDDDDD

const ExamStepD = () => {


   const initialCheckboxValues = {
      checkbox1: true,
      checkbox2: false,
      checkbox3: false,
      checkbox4: false,
      checkbox5: true,
      checkbox6: false,
      checkbox7: false,
      checkbox8: false,
      checkbox9: false,
      checkbox10: false,
      checkbox11: true,
   };

   const [checkboxValues, setCheckboxValues] = useState(initialCheckboxValues);

   return (
      <div className='exam-steps'>
         <div className='ex-sa-1'>
            <table >
               <tr>
                  <td>B. Tech CSE (AI & ML)</td>
                  <td>Sem 7th</td>
                  <td>Exam form no: 4655436546</td>
               </tr>
               <tr>
                  <td>Amrendra Yadav</td>
                  <td>2004282530002</td>
                  <td>Status: Under Proccesing</td>
               </tr>
            </table>
         </div>
         <div className='ex-sa-2'>
            <div className='ex-sa-list'>
               <p className='ex-sa-n'>Selected Subject</p>
               <div className="ex-sa-list-c1" >
                  {Object.keys(checkboxValues).map((checkboxName) => (
                     <label className='exam-sub-li-1' key={checkboxName}>
                        <span style={{ marginLeft: '20px' }}>{` ${checkboxName}`}</span>
                     </label>
                  ))}
               </div>
            </div>
            <div className='ex-sa-list-view' style={{ width: '50%' }}>
               <div>

                  <div className='ex-sd-list-view-1'>
                     <table>
                        <tbody>
                           <tr>
                              <td>Status</td>
                              <td>Failed</td>
                           </tr>
                           <tr>
                              <td>Message</td>
                              <td> Not Mentioned all subject</td>
                           </tr>
                           <tr>
                              <td>Solution</td>
                              <td> Contact to Registrayer Office</td>
                           </tr>
                        </tbody>
                     </table>
                  </div>
                  <p style={{ fontFamily: 'Inria Sans', fontSize: '14px', width: '100%', height: 'auto' }}>

                     *Lorem ipsum dolor sit amet consectetur, adipisicing elit. Impedit quaerat, magnam laborum adipisci molestiae tempora hic eum possimus enim reprehenderit nobis nulla repellendus quisquam eveniet inventore explicabo, aperiam odit molestias.

                  </p>
               </div>
            </div>

         </div>

      </div>
   )
}

/// ---------------------------- EEEEEEEEEEEEEEEEEEE
const ExamStepE = () => {


   const initialCheckboxValues = {
      checkbox1: true,
      checkbox2: false,
      checkbox3: false,
      checkbox4: false,
      checkbox5: true,
      checkbox6: false,
      checkbox7: false,
      checkbox8: false,
      checkbox9: false,
      checkbox10: false,
      checkbox11: true,

   };

   const [checkboxValues, setCheckboxValues] = useState(initialCheckboxValues);

   return (
      <div className='exam-steps'>
         <div className='ex-sa-1'>
            <table >
               <tr>
                  <td>B. Tech CSE (AI & ML)</td>
                  <td>Sem 7th</td>
                  <td>Exam form no: 4655436546</td>
               </tr>
               <tr>
                  <td>Amrendra Yadav</td>
                  <td>2004282530002</td>
                  <td>Onging</td>
               </tr>
            </table>
         </div>
         <div className='ex-sd-2'>
            <p className='ex-sa-n'>Exam Time Table</p>
            <div className='test-scroll'>
               <table>
                  <thead>
                     <tr>
                        <th>Subject</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>A/P</th>
                     </tr>
                  </thead>

                  <tbody>
                     {Object.keys(checkboxValues).map((checkboxName) => (
                        <tr key={checkboxName}>
                           <td>
                              {` ${checkboxName}`}
                           </td>
                           <td>23/03/1002</td>
                           <td>23:10</td>
                           <td>A</td>
                        </tr>
                     ))}
                  </tbody>
               </table>
            </div>

         </div>
      </div>
   )
}


////////// --------------------------FFFFFFFFFFFFFFFFFFF
const ExamStepF = () => {



   const initialCheckboxValues = {
      checkbox1: true,
      checkbox2: false,
      checkbox3: false,
      checkbox4: false,
      checkbox5: true,
      checkbox6: false,
      checkbox7: false,
      checkbox8: false,
      checkbox9: false,
      checkbox10: false,
      checkbox11: true,

   };

   const [checkboxValues, setCheckboxValues] = useState(initialCheckboxValues);

   return (
      <div className='exam-steps'>
         <div className='ex-sa-1 ex-re-t'>
            <table >
               <tr>
                  <td>B. Tech CSE (AI & ML)</td>
                  <td>Sem 7th</td>
                  <td>Exam form no: 4655436546</td>
                  <td>Total:900</td>
                  <td>Back:2</td>
               </tr>
               <tr>
                  <td>Amrendra Yadav</td>
                  <td>2004282530002</td>
                  <td>Pass</td>
                  <td>Obtains: 500</td>
                  <td>clear: 4</td>
               </tr>
            </table>
         </div>
         <div className='ex-sd-2'>
            <p className='ex-sa-n'>Exam Result</p>
            <div className='test-scroll'>
               <table>
                  <thead>
                     <tr>
                        <th>Subject</th>
                        <th>Total</th>
                        <th>Internal</th>
                        <th>External</th>
                        <th>Obtain</th>
                     </tr>
                  </thead>

                  <tbody>
                     {Object.keys(checkboxValues).map((checkboxName) => (
                        <tr key={checkboxName}>
                           <td>
                              {` ${checkboxName}`}
                           </td>
                           <td>150</td>
                           <td>100</td>
                           <td>30</td>
                           <td>130</td>
                        </tr>
                     ))}
                  </tbody>
               </table>
            </div>

         </div>
      </div>
   )

}

/// -----------------------GGGGGGGGGGGGGG
const ExamStepG = () => {
   const initialCheckboxValues = {
      checkbox1: true,
      checkbox2: false,
      checkbox3: false,
      checkbox4: false,
      checkbox5: true,
      checkbox6: false,
      checkbox7: false,
      checkbox8: false,
      checkbox9: false,
      checkbox10: false,
      checkbox11: true,

   };

   const [checkboxValues, setCheckboxValues] = useState(initialCheckboxValues);

   const handleCheckboxChange = (checkboxName) => {
      setCheckboxValues((prevValues) => ({
         ...prevValues,
         [checkboxName]: !prevValues[checkboxName],
      }));
   };

   const submitHandle = () => {
      const trueCheckboxValues = Object.entries(checkboxValues).reduce(
         (acc, [key, value]) => {
            if (value === true) {
               acc[key] = value;
            }
            return acc;
         },
         {}
      );
      console.log(trueCheckboxValues)
   };

   return (
      <div className='exam-steps'>
         <div className='ex-sa-1 ex-re-t'>
            <table >
               <tr>
                  <td>B. Tech CSE (AI & ML)</td>
                  <td>Sem 7th</td>
                  <td>Exam form no: 4655436546</td>
                  <td>Total:900</td>
                  <td>Back:2</td>
               </tr>
               <tr>
                  <td>Amrendra Yadav</td>
                  <td>2004282530002</td>
                  <td>Pass</td>
                  <td>Obtains: 500</td>
                  <td>clear: 4</td>
               </tr>
            </table>
         </div>
         <div className='ex-sd-2'>
            <p className='ex-sa-n'>Result</p>
            <div className='test-scroll'>
               <table>
                  <thead>
                     <tr>
                        <th>Subject</th>
                        <th>Total</th>
                        <th>Obtain</th>
                        <th>Download Copy</th>
                     </tr>
                  </thead>

                  <tbody>
                     {Object.keys(checkboxValues).map((checkboxName) => (
                        <tr key={checkboxName}>
                           <td>
                              <input
                                 type="checkbox"
                                 checked={checkboxValues[checkboxName]}
                                 onChange={() => handleCheckboxChange(checkboxName)}
                              />
                              {` ${checkboxName}`}
                           </td>
                           <td>150</td>
                           <td>130</td>
                           <td><button>Download</button></td>
                        </tr>
                     ))}
                  </tbody>
               </table>
            </div>

            <div className='ex-res-btn'>
               <button onClick={submitHandle}>Apply </button>
            </div>

         </div>
      </div>
   )
}
export default Exam
