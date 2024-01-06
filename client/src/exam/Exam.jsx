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
         case 'H':
            return <ExamStepH />
         default:
            break;
      }
   }
   return (
      <>
         <ExamStatus status={4} />
         {
            setps('C')
         }

      </>

   )
}

const ExamStatus = ({ status }) => {

   return (
      <div className='exam-status'>

         <div className='es-1'>

            <div className={`status ${status >= 1 ? (status !== 1 ? 'active2' : 'active') : ''}`}>
               Start
            </div>

            <div className={`status ${status >= 2 ? (status !== 2 ? 'active2' : 'active') : ''}`} >
               Fill Form
            </div>

            <div className={`status ${status >= 3 ? (status !== 3 ? 'active2' : 'active') : ''}`} >
               Payment
            </div>
            <div className={`status ${status >= 4 ? (status !== 4 ? 'active2' : 'active') : ''}`} >
               Correction
            </div>
            <div className={`status ${status >= 5 ? (status !== 5 ? 'active2' : 'active') : ''}`} >
               Verification
            </div>
            <div className={`status ${status >= 6 ? (status !== 6 ? 'active2' : 'active') : ''}`} >
               Schedule
            </div>
            <div className={`status ${status >= 7 ? (status !== 7 ? 'active2' : 'active') : ''}`} >
               Exam
            </div>
            <div className={`status ${status >= 8 ? (status !== 8 ? 'active2' : 'active') : ''}`} >
               Result
            </div>

         </div>

      </div>
   )
}

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
               <tr>
                  <td>B. Tech CSE (AI & ML)</td>
                  <td>Sem 7th</td>
               </tr>
               <tr>
                  <td>Amrendra Yadav</td>
                  <td>2004282530002</td>
               </tr>
            </table>
         </div>
         <div className='ex-sa-2'>
            <div className='ex-sa-list'>
               <p className='ex-sa-n'>Exam List</p>
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

const ExamStepB = () => {
   return (
      <div className='exam-steps'>
         <div className='ex-sa-1'>
            <table >
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
const ExamStepC = () => {
   return (
      <div className='exam-steps'>
         <div className='ex-sa-1'>
            <table >
               <tr>
                  <td>B. Tech CSE (AI & ML)</td>
                  <td>Sem 7th</td>
               </tr>
               <tr>
                  <td>Amrendra Yadav</td>
                  <td>2004282530002</td>
               </tr>
            </table>
         </div>
         <div className='ex-sa-2'>
            <div className='ex-sa-list'>
               <p className='ex-sa-n'>Exam List</p>
               <div className="ex-sa-list-c1" >

               </div>
            </div>
            <div className='ex-sa-list-view'>
               <div className='ex-sa-list-view-1'>
                  <p className='ex-sa-n'>Selected Subject</p>
                  <div className='ex-sa-list-view-1-1'>

                  </div>
               </div>
               <button >Submit</button>
            </div>
         </div>

      </div>
   )
}

const ExamStepD = () => {
   return (
      <div>
         Setps D
      </div>
   )
}
const ExamStepE = () => {
   return (
      <div>
         Setps E
      </div>
   )
}
const ExamStepF = () => {
   return (
      <div>
         Setps F
      </div>
   )
}
const ExamStepG = () => {
   return (
      <div>
         Setps G
      </div>
   )
}
const ExamStepH = () => {
   return (
      <div>
         Setps H
      </div>
   )
}

export default Exam
