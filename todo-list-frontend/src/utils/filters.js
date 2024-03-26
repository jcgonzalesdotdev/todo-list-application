import { add } from 'date-fns';
import { CONSTANTS } from './constants'
import { COMMON_UTILS } from './common';
/**
 *
 * @param { boolean } langFlg, true = JP, false = EN
 * @param { int } noOfDays
 * @returns formattedDateString
 */
function daysFilter(langFlg, noOfDays) {
  const today = new Date();
  const daysToAdd = parseInt(noOfDays)
  const newDate = add(today, { days: daysToAdd });

  const year = newDate.getFullYear();
  const month = String(newDate.getMonth() + 1).padStart(2, '0'); // January is 0
  const day = String(newDate.getDate()).padStart(2, '0');

  const date = `${year}-${month}-${day}`;
  let formattedDateString = '';

  if (langFlg === false) {
    formattedDateString = date;
  } else {
    formattedDateString = COMMON_UTILS.convertToJapaneseDate(date);
  }
  console.log(formattedDateString)
  return formattedDateString
}

function startsTomorrow(searchInput, lang) {
  if (
    (lang === false &&
      (searchInput.toLowerCase() === 'starts tomorrow' ||
        searchInput.toLowerCase() === 'start tomorrow' ||
        searchInput.toLowerCase() === 'tomorrow starts' ||
        searchInput.toLowerCase() === 'tomorrow start')) ||
    (lang === true &&
      (searchInput === '明日から始まる' ||
        searchInput === 'あしたからはじまる' ||
        searchInput === '明日からはじまる' ||
        searchInput === 'あしたから始まる'))
  ) {
    return true
  }
  return false
}

function startedYesterday(searchInput, lang) {
  if (
    (lang === false &&
      (searchInput.toLowerCase() === 'started yesterday' ||
        searchInput.toLowerCase() === 'start yesterday' ||
        searchInput.toLowerCase() === 'yesterday start')) ||
    (lang === true &&
      (searchInput === '昨日から始まりました' ||
        searchInput === '昨日から始まった' ||
        searchInput === 'きのうからはじまりました' ||
        searchInput === 'きのうからはじまった' ||
        searchInput === 'きのうから始まりました' ||
        searchInput === '昨日からはじまった' ||
        searchInput === 'きのうから始まった'))
  ) {
    return true
  }
  return false
}

function startsToday(searchInput, lang) {
  if (
    (lang === false &&
      (searchInput.toLowerCase() === 'starts today' ||
        searchInput.toLowerCase() === 'start today' ||
        searchInput.toLowerCase() === 'today starts' ||
        searchInput.toLowerCase() === 'today start')) ||
    (lang === true &&
      (searchInput === '今日から始まる' ||
        searchInput === 'きょうからはじまる' ||
        searchInput === '今日からはじまる' ||
        searchInput === 'きょうから始まる' ||
        searchInput === '今日から始まります' ||
        searchInput === 'きょうから始まります' ||
        searchInput === '今日からはじまります' ||
        searchInput === 'きょうからはじまります'))
  ) {
    return true
  }
  return false
}

/**
 *
 * Search input should be 'starts $day days from now'
 * EN ONLY
 *
 * @param {*} searchInput
 * @param {*} lang
 */
function startsXdaysFromNow(searchInput) {
  let wordsArr = searchInput.toLowerCase().split(/\s+/)
  const result = startsEndsXFromNow(wordsArr, CONSTANTS.starts_days_from_now)

  if(result && (
    wordsArr.includes('starts') ||
    wordsArr.includes('start') ||
    wordsArr.includes('begins') ||
    wordsArr.includes('begin') ||
    wordsArr.includes('do'))){
      return true
  }
  return false
}

/**
 *
 * Search input should be 'ends $day days from now'
 * EN ONLY
 *
 * @param {*} searchInput
 * @param {*} lang
 */
function endsXdaysFromNow(searchInput) {
  let wordsArr = searchInput.toLowerCase().split(/\s+/)
  const result = startsEndsXFromNow(wordsArr, CONSTANTS.ends_days_from_now)
  return result
}

function startsEndsXFromNow(wordsArr, constMap){
// Count the number of matches between words and starts_days_from_now array
let matchCount = 0
let numberIndex = null;
for (const word of wordsArr) {
  if (constMap.includes(word)) {
    matchCount++
  }
  
  if (numberIndex === null){
    numberIndex = COMMON_UTILS.getIndexOfNumberInSearchInput(CONSTANTS.numberMap, word, wordsArr)
  }
}
if (matchCount >= 4) {
  if (!isNaN(wordsArr[numberIndex])) {
    return true
  } else {
    let convertedNumber = [CONSTANTS.numberMap.values()].includes(wordsArr[parseInt(numberIndex)])
    if (convertedNumber) {
      return true
    }
    return false
  }
}
return false
}

export const FILTERS = {
  startsTomorrow,
  startedYesterday,
  daysFilter,
  startsToday,
  startsXdaysFromNow,
  endsXdaysFromNow
}
