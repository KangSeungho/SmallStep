package app.sosocom.smallstep.presentation.ui.diary

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import app.sosocom.smallstep.presentation.R
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.diary_title)
                    )
                }
            )
        }
    ) { innerPadding ->
        val screenModifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()

        DiaryInnerContent(
            viewModel = viewModel,
            modifier = screenModifier
        )
    }
}

@Composable
private fun DiaryInnerContent(
    viewModel: DiaryViewModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val date = viewModel.diary.value?.baseDate?.format(DateTimeFormatter.ofPattern("YYYY년 MM월 dd일 E요일", Locale.KOREA)) ?: ""
            Text(text = date)
        }
    }
}