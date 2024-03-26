/**
 * Convert date string yyyy年MM月dd日 to date YYYY-MM-DD format and vice versa
 * @param {string} dateString - The date string to convert.
 * @param {dateFormatCheck} dateFormatCheck - True = EN, False = JP
 * @returns {date} The formatted date in 'YYYY-MM-DD' format.
 */
function stringToDate(dateString, dateFormatCheck) {
  let formattedDate = null
  console.log('DATE FORMAT CHECK: ', dateFormatCheck)
  if (dateString != '') {
    //True = EN, False = JP
    if (dateFormatCheck) {
      let parts = dateString.split(/年|月|日/)
      let date = new Date(parts[0], parts[1] - 1, parts[2])
      // Format the date into YYYY-MM-DD format
      formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1)
        .toString()
        .padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`
    } else {
      formattedDate = dateString
    }
  }
  return formattedDate
}

/**
 * Convert date string from 'YYYY-MM-DD' to 'YYYY年MM月DD日' format.
 * @param {string} dateString - The date string to convert.
 * @returns {string} The formatted date string in 'YYYY年MM月DD日' format.
 */
function convertToJapaneseDate(dateString) {
  let formattedDate = ''

  if (dateString !== null && dateString) {
    const [year, month, day] = dateString.split('-').map(Number)

    if (!isNaN(year) && !isNaN(month) && !isNaN(day)) {
      formattedDate = `${year}年${month.toString().padStart(2, '0')}月${day.toString().padStart(2, '0')}日`
    } else {
      console.error('Invalid date format:', dateString)
    }
  }

  return formattedDate
}

/**
 * Convert date string from 'YYYY年MM月DD日' to 'YYYY-MM-DD' format.
 * @param {string} japaneseDateString - The Japanese date string to convert.
 * @returns {string} The formatted date string in 'YYYY-MM-DD' format.
 */
function convertToEnglishDate(japaneseDateString) {
  let formattedDate = ''
  console.log(japaneseDateString)
  if (japaneseDateString !== null && japaneseDateString) {
    const regex = /(\d{4})年(\d{1,2})月(\d{1,2})日/
    const match = japaneseDateString.match(regex)

    if (match) {
      const [, year, month, day] = match.map(Number)
      formattedDate = `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`
    } else {
      console.error('Invalid Japanese date format:', japaneseDateString)
    }
  }

  return formattedDate
}

/**
 * 
 * @param { boolean } langFlg, true = JP, false = EN
 * @param { int } noOfDays 
 * @returns formattedDateString
 */
function daysFilter(langFlg ,noOfDays) {
  const today = new Date()
  const tomorrow = new Date(today)
  tomorrow.setDate(tomorrow.getDate() + noOfDays)

  const year = tomorrow.getFullYear()
  const month = String(tomorrow.getMonth() + 1).padStart(2, '0') // January is 0
  const day = String(tomorrow.getDate()).padStart(2, '0')

  const date = `${year}-${month}-${day}`
  let formattedDateString = ''
  if (langFlg === false) {
    formattedDateString = date
  } else {
    formattedDateString = COMMON_UTILS.convertToJapaneseDate(date)
  }

  return formattedDateString;
}

export const COMMON_UTILS = {
  stringToDate,
  convertToJapaneseDate,
  convertToEnglishDate,
  daysFilter
}
