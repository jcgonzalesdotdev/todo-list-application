function setScreenLabelsEn(screenItems, labels){
        screenItems.taskId = labels.EN.lbl_taskid_en
        screenItems.taskTitle = labels.EN.lbl_title_en
        screenItems.taskDesciption = labels.EN.lbl_description_en
        screenItems.taskStart_date = labels.EN.lbl_start_date_en
        screenItems.taskEnd_date = labels.EN.lbl_end_date_en
        screenItems.taskStatus = labels.EN.lbl_status_en

        return screenItems;
}

function setScreenLabelsJp(screenItems, labels){
    console.log(labels)
    screenItems.taskId = labels.JP.lbl_taskid_jp
    screenItems.taskTitle = labels.JP.lbl_title_jp
    screenItems.taskDesciption = labels.JP.lbl_description_jp
    screenItems.taskStart_date = labels.JP.lbl_start_date_jp
    screenItems.taskEnd_date = labels.JP.lbl_end_date_jp
    screenItems.taskStatus = labels.JP.lbl_status_jp

    return screenItems;
}

export const SCREEN_MODIFIERS = {
    setScreenLabelsEn,
    setScreenLabelsJp
}