package com.example.dumbpasswordgenerator.ui.password

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun PasswordScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val viewModel = viewModel<PasswordViewModel>()

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.checkPassword()
                    },
                    modifier = Modifier
                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(4.dp)

                ) {
                    Text(text = "Check")
                }

                Text(
                    text = viewModel.feedback,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.05f)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(top = 6.dp, start = 4.dp, end = 4.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFffe972), shape = RoundedCornerShape(16.dp))
                    .border(width = 2.dp, color = Color.DarkGray, shape = RoundedCornerShape(16.dp))
                    .padding(12.dp)
            ) {
                Column {
                    val sortedRules = viewModel.rules.sortedByDescending { rule ->
                        when (rule) {
                            viewModel.lengthFulfilledString -> if (!viewModel.lengthFulfilled) 1 else 0
                            viewModel.containsTwoNumbersString -> if (!viewModel.containsTwoNumbers) 1 else 0
                            viewModel.containsTwoLettersString -> if (!viewModel.containsTwoLetters) 1 else 0
                            viewModel.containsTwoSpecialsString -> if (!viewModel.containsTwoSpecials) 1 else 0
                            viewModel.minOneUpperCaseString -> if (!viewModel.minOneUpperCase) 1 else 0
                            viewModel.minOneLowerCaseString -> if (!viewModel.minOneLowerCase) 1 else 0
                            viewModel.noSameTwoLetterNeighborsString -> if (!viewModel.noSameTwoLetterNeighbors) 1 else 0
                            viewModel.containEmojiString -> if (!viewModel.containEmoji) 1 else 0
                            viewModel.startWithSpecialString -> if (!viewModel.startWithSpecial) 1 else 0
                            viewModel.endOnLetterString -> if (!viewModel.endOnLetter) 1 else 0
                            viewModel.maximumLengthString -> if (!viewModel.maximumLength) 1 else 0
                            else -> 0
                        }
                    }

                    sortedRules.forEach { rule ->
                        viewModel.RuleItem(
                            rule = rule,
                            isFulfilled = when (rule) {
                                viewModel.lengthFulfilledString -> viewModel.lengthFulfilled
                                viewModel.containsTwoNumbersString -> viewModel.containsTwoNumbers
                                viewModel.containsTwoLettersString -> viewModel.containsTwoLetters
                                viewModel.containsTwoSpecialsString -> viewModel.containsTwoSpecials
                                viewModel.minOneUpperCaseString -> viewModel.minOneUpperCase
                                viewModel.minOneLowerCaseString -> viewModel.minOneLowerCase
                                viewModel.noSameTwoLetterNeighborsString -> viewModel.noSameTwoLetterNeighbors
                                viewModel.containEmojiString -> viewModel.containEmoji
                                viewModel.startWithSpecialString -> viewModel.startWithSpecial
                                viewModel.endOnLetterString -> viewModel.endOnLetter
                                viewModel.maximumLengthString -> viewModel.maximumLength
                                else -> false
                            }
                        )
                    }
                }
            }
        }
    }
}
