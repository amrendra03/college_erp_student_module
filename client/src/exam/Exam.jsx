import React from 'react';
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


                  {Array.from({ length: 60 }).map((_, index) => (
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
         <ExamStatus />
         {
            setps('A')
         }

      </>

   )
}

const ExamStatus = (data) => {
   return (
      <div className='exam-status'>

         <div className='es-1'>
            <div className='es-2'>
               <div className='es-3'>
                  <div className='es-3-1' />
                  <div className='es-3-2' />
               </div>
               <div className='es-4'>
                  Start
               </div>
            </div>
         </div>

      </div>
   )
}



const ExamStepA = () => {
   return (
      <div className='exam-steps'>
         Setps A
      </div>
   )
}
const ExamStepB = () => {
   return (
      <div className='exam-steps'>
         Setps B
      </div>
   )
}
const ExamStepC = () => {
   return (
      <div>
         Setps A
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
