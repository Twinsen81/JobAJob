package jobajob.feature.dashboard.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy")
internal data class VacancyLocalDto(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "details")
    val details: String,

    @ColumnInfo(name = "salary")
    val salary: String,

    @ColumnInfo(name = "payed")
    val payed: Boolean
)