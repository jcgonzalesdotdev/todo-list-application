export const API_BASE_URL = 'http://localhost:8080/api/tasks'

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

export const SCREEN_LABELS = {
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
  }
}
