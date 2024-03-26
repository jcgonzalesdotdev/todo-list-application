export const API_BASE_URL = 'http://localhost:8080/api/tasks'

const EMPTY = ''
const WHITE_SPACE = ' '
const lbl_taskid_en = 'Task ID'
const lbl_title_en = 'Title'
const lbl_description_en = 'Description'
const lbl_start_date_en = 'Start Date'
const lbl_end_date_en = 'End Date'
const lbl_status_en = 'Status'
const lbl_search_en = 'Search'
const lbl_taskid_jp = 'タスクID'
const lbl_title_jp = 'タイトル'
const lbl_description_jp = '詳細'
const lbl_start_date_jp = '開始日'
const lbl_end_date_jp = '終了日'
const lbl_status_jp = 'ステータス'
const lbl_search_jp = '検索'
const starts_days_from_now = ['begins','begin','starts', 'start', 'from', 'now', 'days', 'day', 'today', 'do']
const ends_days_from_now = ['ends', 'end', 'from', 'now', 'days', 'day', 'today']

const numberMap = new Map([
  ['one', 1],
  ['two', 2],
  ['three', 3],
  ['four', 4],
  ['five', 5],
  ['six', 6],
  ['seven', 7],
  ['eight', 8],
  ['nine', 9],
  ['ten', 10],
  ['eleven', 11],
  ['twelve', 12],
  ['thirteen', 13],
  ['fourteen', 14],
  ['fifteen', 15],
  ['sixteen', 16],
  ['seventeen', 17],
  ['eighteen', 18],
  ['nineteen', 19],
  ['twenty', 20],
  ['twenty-one', 21],
  ['twenty-two', 22],
  ['twenty-three', 23],
  ['twenty-four', 24],
  ['twenty-five', 25],
  ['twenty-six', 26],
  ['twenty-seven', 27],
  ['twenty-eight', 28],
  ['twenty-nine', 29],
  ['thirty', 30],
  ['thirty-one', 31],
  ['thirty-two', 32],
  ['thirty-three', 33],
  ['thirty-four', 34],
  ['thirty-five', 35],
  ['thirty-six', 36],
  ['thirty-seven', 37],
  ['thirty-eight', 38],
  ['thirty-nine', 39],
  ['forty', 40],
  ['forty-one', 41],
  ['forty-two', 42],
  ['forty-three', 43],
  ['forty-four', 44],
  ['forty-five', 45],
  ['forty-six', 46],
  ['forty-seven', 47],
  ['forty-eight', 48],
  ['forty-nine', 49],
  ['fifty', 50],
  ['fifty-one', 51],
  ['fifty-two', 52],
  ['fifty-three', 53],
  ['fifty-four', 54],
  ['fifty-five', 55],
  ['fifty-six', 56],
  ['fifty-seven', 57],
  ['fifty-eight', 58],
  ['fifty-nine', 59],
  ['sixty', 60],
  ['sixty-one', 61],
  ['sixty-two', 62],
  ['sixty-three', 63],
  ['sixty-four', 64],
  ['sixty-five', 65],
  ['sixty-six', 66],
  ['sixty-seven', 67],
  ['sixty-eight', 68],
  ['sixty-nine', 69],
  ['seventy', 70],
  ['seventy-one', 71],
  ['seventy-two', 72],
  ['seventy-three', 73],
  ['seventy-four', 74],
  ['seventy-five', 75],
  ['seventy-six', 76],
  ['seventy-seven', 77],
  ['seventy-eight', 78],
  ['seventy-nine', 79],
  ['eighty', 80],
  ['eighty-one', 81],
  ['eighty-two', 82],
  ['eighty-three', 83],
  ['eighty-four', 84],
  ['eighty-five', 85],
  ['eighty-six', 86],
  ['eighty-seven', 87],
  ['eighty-eight', 88],
  ['eighty-nine', 89],
  ['ninety', 90],
  ['ninety-one', 91],
  ['ninety-two', 92],
  ['ninety-three', 93],
  ['ninety-four', 94],
  ['ninety-five', 95],
  ['ninety-six', 96],
  ['ninety-seven', 97],
  ['ninety-eight', 98],
  ['ninety-nine', 99],
  ['one-hundred', 100]
])

export const CONSTANTS = {
  EN: {
    lbl_taskid_en,
    lbl_title_en,
    lbl_description_en,
    lbl_start_date_en,
    lbl_end_date_en,
    lbl_status_en,
    lbl_search_en,
    status_en: {
      NOT_YET_STARTED: 'NOT YET STARTED',
      IN_PROGRESS: 'IN PROGRESS',
      COMPLETED: 'COMPLETED',
      DELAYED: 'DELAYED',
      PENDING: 'PENDING',
      CANCELLED: 'CANCELLED',
    }
  },
  JP: {
    lbl_taskid_jp,
    lbl_title_jp,
    lbl_description_jp,
    lbl_start_date_jp,
    lbl_end_date_jp,
    lbl_status_jp,
    lbl_search_jp,
    status_jp: {
      NOT_YET_STARTED: '未開始',
      IN_PROGRESS: '進行中',
      COMPLETED: '完了',
      DELAYED: '遅延',
      PENDING: '保留',
      CANCELLED: 'キャンセル/取り消し',
    }
  },
  starts_days_from_now,
  ends_days_from_now,
  numberMap,
  EMPTY,
  WHITE_SPACE
}
