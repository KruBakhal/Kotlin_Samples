package com.example.myapplication.TicTacTo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin_samples.TicToc.TicTacTo.Model.MatrixModel
import com.example.kotlin_samples.TicToc.TicTacTo.TicTacAdapter
import com.example.kotlin_samples.databinding.ActivityTicTacBinding

class TicTacActivity : AppCompatActivity() {
    private lateinit var view: ActivityTicTacBinding
    private lateinit var list: java.util.ArrayList<MatrixModel>
    var currentActive: Int = 0
    var span: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContentView(R.layout.activity_tic_tac);
        view = ActivityTicTacBinding.inflate(layoutInflater)
        setContentView(view.root)

        list = ArrayList<MatrixModel>()

        view.btnSubmit.setOnClickListener {

            span = view.edInput.text?.toString()?.toInt()!!

            setup(span)
        }


    }

    private fun setup(span: Int) {
        for (i in 0 until span) {
            for (j in 0 until span) {
                list.add(MatrixModel(i, j))
            }
        }
        view.rvTicTac.layoutManager = GridLayoutManager(this@TicTacActivity, span)
        view.rvTicTac.adapter = TicTacAdapter(list, 1, object : TicTacAdapter.TicInterface {
            override fun onCLickBox(position: Int, matrixModel: MatrixModel) {
                // initial stage

                if (currentActive == 0) {
//                    currentActive = 1
                    matrixModel.user = 0
                } else {
//                    currentActive = 0
                    matrixModel.user = 1
                }
                list.set(position, matrixModel)


                if (check_top_bottom_with_column(position, list)) {

                    Toast.makeText(this@TicTacActivity,
                        "User ${currentActive + 1} Winnner",
                        Toast.LENGTH_LONG).show()
                } else if (check_side_by_side(position, list)) {
                    Toast.makeText(this@TicTacActivity,
                        "User ${currentActive + 1} Winnner",
                        Toast.LENGTH_LONG).show()

                } else if (check_diagonal_top_left_bottom_right(position, list)) {
                    Toast.makeText(this@TicTacActivity,
                        "User ${currentActive + 1} Winnner",
                        Toast.LENGTH_LONG).show()

                } else if (check_diagonal_bottom_left_top_right(position, list)) {
                    Toast.makeText(this@TicTacActivity,
                        "User ${currentActive + 1} Winnner",
                        Toast.LENGTH_LONG).show()

                }
                if (currentActive == 0) {
                    currentActive = 1
                } else {
                    currentActive = 0
                }

            }

        })
    }

    private fun check_diagonal_bottom_left_top_right(
        position: Int,
        list: java.util.ArrayList<MatrixModel>,
    ): Boolean {
        var active = -1
        var count = -1

        var pos3 = position
        var pos4 = position
        var isFirst3 = true
        var isFirst4 = true
        Log.d("TAG", "check_diagonal: part1")


        for (j in 0 until span) {
            if (!isFirst3) {
                pos3 = pos3 - ((span - 1))
            } else
                isFirst3 = false

            if (pos3 >= 0 && pos3 < list.size) {
//                Log.d("TAG", "user 2 --> : " + pos3)

                val matrixModel: MatrixModel = list.get(pos3)
                if (matrixModel.user == 0) {
                    if (active == 1) {
                        break
                    }
                    active = 0;
                    count++
                    Log.d("TAG", "user 1: " + pos3 + " active " + active + "  count: " + count)

                } else if (matrixModel.user == 1) {
                    if (active == 0) {
                        break
                    }
                    active = 1
                    count++
                    //Log.d("TAG", "user 2: " + pos3 + " active " + active + "  count: " + count)

                }
            } else {
                break
            }
        }
        Log.d("TAG", "check_diagonal: part2")
        for (j in 0 until span) {
            if (!isFirst4) {
                pos4 = pos4 + ((span - 1))
            } else
                isFirst4 = false

            if (pos4 >= 0 && pos4 < list.size) {
//                Log.d("TAG", "user 2 --> : " + pos4)

                val matrixModel: MatrixModel = list.get(pos4)
                if (matrixModel.user == 0) {
                    if (active == 1) {
                        break
                    }
                    active = 0;
                    count++
                    Log.d("TAG", "user 1: " + pos4 + " active " + active + "  count: " + count)

                } else if (matrixModel.user == 1) {
                    if (active == 0) {
                        break
                    }
                    active = 1
                    count++
                    //   Log.d("TAG", "user 2: " + pos4 + " active " + active + "  count: " + count)

                }
            } else {
                break
            }
        }


        if (count == span) {
            return true
        } else {
            return false
        }
    }

    private fun check_diagonal_top_left_bottom_right(
        position: Int,
        list: java.util.ArrayList<MatrixModel>,
    ): Boolean {
        var active = -1
        var count = -1

        var pos3 = position
        var pos4 = position
        var isFirst3 = true
        var isFirst4 = true
        Log.d("TAG", "check_diagonal: part1")

        for (j in 0 until span) {
            if (!isFirst3) {
                pos3 = pos3 - (span + 1)
            } else
                isFirst3 = false

            if (pos3 >= 0 && pos3 < list.size) {
//                Log.d("TAG", "user 2 --> : " + pos3)

                val matrixModel: MatrixModel = list.get(pos3)
                if (matrixModel.user == 0) {
                    if (active == 1) {
                        break
                    }
                    active = 0;
                    count++
                    Log.d("TAG", "user 1: " + pos3 + " active " + active + "  count: " + count)

                } else if (matrixModel.user == 1) {
                    if (active == 0) {
                        break
                    }
                    active = 1
                    count++
                    //Log.d("TAG", "user 2: " + pos3 + " active " + active + "  count: " + count)

                }
            } else {
                break
            }
        }
        Log.d("TAG", "check_diagonal: part2")
        for (j in 0 until span) {
            if (!isFirst4) {
                pos4 = pos4 + (span + 1)
            } else
                isFirst4 = false

            if (pos4 >= 0 && pos4 < list.size) {
//                Log.d("TAG", "user 2 --> : " + pos4)

                val matrixModel: MatrixModel = list.get(pos4)
                if (matrixModel.user == 0) {
                    if (active == 1) {
                        break
                    }
                    active = 0;
                    count++
                    Log.d("TAG", "user 1: " + pos4 + " active " + active + "  count: " + count)

                } else if (matrixModel.user == 1) {
                    if (active == 0) {
                        break
                    }
                    active = 1
                    count++
                    //   Log.d("TAG", "user 2: " + pos4 + " active " + active + "  count: " + count)

                }
            } else {
                break
            }
        }


        if (count == span) {
            return true
        } else {
            return false
        }
    }

    private fun check_side_by_side(position: Int, list: java.util.ArrayList<MatrixModel>): Boolean {
        var active = -1
        var count = -1

        var pos3 = position
        var pos4 = position
        var isFirst3 = true
        var isFirst4 = true
        for (j in 0 until span) {
            if (!isFirst3) {
                pos3 = pos3 - 1
            } else
                isFirst3 = false

            if (pos3 >= 0 && pos3 < list.size) {
                Log.d("TAG", "user 2 --> : " + pos3)

                val matrixModel: MatrixModel = list.get(pos3)
                if (matrixModel.user == 0) {
                    if (active == 1) {
                        break
                    }
                    active = 0;
                    count++
                    Log.d("TAG", "user 1: " + pos3 + " active " + active + "  count: " + count)

                } else if (matrixModel.user == 1) {
                    if (active == 0) {
                        break
                    }
                    active = 1
                    count++
                    Log.d("TAG", "user 2: " + pos3 + " active " + active + "  count: " + count)

                }
            } else {
                break
            }
        }

        for (j in 0 until span) {
            if (!isFirst4) {
                pos4 = pos4 + 1
            } else
                isFirst4 = false

            if (pos4 >= 0 && pos4 < list.size) {
                Log.d("TAG", "user 2 --> : " + pos4)

                val matrixModel: MatrixModel = list.get(pos4)
                if (matrixModel.user == 0) {
                    if (active == 1) {
                        break
                    }
                    active = 0;
                    count++
                    Log.d("TAG", "user 1: " + pos4 + " active " + active + "  count: " + count)

                } else if (matrixModel.user == 1) {
                    if (active == 0) {
                        break
                    }
                    active = 1
                    count++
                    Log.d("TAG", "user 2: " + pos4 + " active " + active + "  count: " + count)

                }
            } else {
                break
            }
        }


        if (count == span) {
            return true
        } else {
            return false
        }
    }

    private fun check_top_bottom_with_column(
        position: Int,
        list: java.util.ArrayList<MatrixModel>,
    ): Boolean {
        var active = -1
        var count = -1

        var pos1 = position
        var pos2 = position
        var pos3 = position
        var pos4 = position
        var isFirst1 = true
        var isFirst2 = true
        var isFirst3 = true
        var isFirst4 = true

        for (j in 0 until span) {
            if (!isFirst1) {
                pos1 = pos1 - span
            } else
                isFirst1 = false


            if (pos1 >= 0 && pos1 < list.size) {
                Log.d("TAG", "user 1: " + pos1)

                val matrixModel: MatrixModel = list.get(pos1)
                if (matrixModel.user == 0) {
                    if (active == 1) {
                        break
                    }
                    active = 0;
                    count++
                    Log.d("TAG", "user 1: " + pos1 + " active " + active + "  count: " + count)

                } else if (matrixModel.user == 1) {
                    if (active == 0) {
                        break
                    }
                    active = 1
                    count++

                    Log.d("TAG", "user 2: " + pos1 + " active " + active + "  count: " + count)
                }
            } else {
                break
            }
        }

        for (j in 0 until span) {
            if (!isFirst2) {
                pos2 = pos2 + span
            } else
                isFirst2 = false

            if (pos2 >= 0 && pos2 < list.size) {
                Log.d("TAG", "user 2 --> : " + pos2)

                val matrixModel: MatrixModel = list.get(pos2)
                if (matrixModel.user == 0) {
                    if (active == 1) {
                        break
                    }
                    active = 0;
                    count++
                    Log.d("TAG", "user 1: " + pos2 + " active " + active + "  count: " + count)

                } else if (matrixModel.user == 1) {
                    if (active == 0) {
                        break
                    }
                    active = 1
                    count++
                    Log.d("TAG", "user 2: " + pos2 + " active " + active + "  count: " + count)

                }
            } else {
                break
            }
        }

        return count == span
    }

    private fun check_left_to_right(
        position: Int,
        list: java.util.ArrayList<MatrixModel>,
    ): Boolean {
        var a = 0
        var b = 0

        var i1 = list.get(position).row
        var j1 = list.get(position).column
        var count = -1
        for (i in 0 until list.size) {
            i1 = i1 - 1
            j1 = list.get(position).column
            val matrixModel: MatrixModel = list.get(position)

            if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                if (matrixModel.user == 0) {
                    if (a == 1) {
                        break
                    }
                    a = 0;
                    count++
                } else if (matrixModel.user == 1) {
                    if (a == 0) {
                        break
                    }
                    a = 1
                    count++
                } else {
                    a = -1
                }
            }
            if (count == span) {
                return true
            }
            count = 0

            i1 = list.get(position).row
            j1 = list.get(i).column + 1

            if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                if (matrixModel.user == 0) {
                    if (a == 1) {
                        break
                    }
                    a = 0;

                    count++;
                } else if (matrixModel.user == 1) {
                    if (a == 0) {
                        break
                    }
                    a = 1
                    count++;
                } else {
                    a = -1
                }
            }

        }
        if (count == span) {
            return true
        } else {
            return false
        }
    }


    private fun check_bottom_right_to_top_left(
        position: Int,
        list: java.util.ArrayList<MatrixModel>,
    ): Boolean {
        var a = 0
        var b = 0

        var i1 = list.get(position).row
        var j1 = list.get(position).column
        var count = -1
        for (i in 0 until list.size) {
            i1 = i1 - 1
            j1 = j1 - 1
            val matrixModel: MatrixModel = list.get(position)

            if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                if (matrixModel.user == 0) {
                    if (a == 1) {
                        break
                    }
                    a = 0;
                    count++
                } else if (matrixModel.user == 1) {
                    if (a == 0) {
                        break
                    }
                    a = 1
                    count++
                } else {
                    a = -1
                }
            }

            i1 = i1 + 1
            j1 = j1 + 1

            if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                if (matrixModel.user == 0) {
                    if (a == 1) {
                        break
                    }
                    a = 0;

                    count++;
                } else if (matrixModel.user == 1) {
                    if (a == 0) {
                        break
                    }
                    a = 1
                    count++;
                } else {
                    a = -1
                }
            }

        }
        if (count == span) {
            return true
        } else {
            return false
        }
    }

    private fun check_bottom_left_to_top_right(
        position: Int,
        list: ArrayList<MatrixModel>,
    ): Boolean {
        var a = 0
        var b = 0
        var i1 = list.get(position).row
        var j1 = list.get(position).column
        var count = -1
        for (i in list.get(position).row until list.size) {
            for (j in list.get(position).column until list.size) {
                i1 = i1 - 1
                j1 = j1 + 1
                val matrixModel: MatrixModel = list.get(position)

                if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                    if (matrixModel.user == 0) {
                        if (a == 1) {
                            break
                        }
                        a = 0;
                        count++
                    } else if (matrixModel.user == 1) {
                        if (a == 0) {
                            break
                        }
                        a = 1
                        count++
                    } else {
                        a = -1
                    }
                }
                i1 = list.get(position).row
                j1 = list.get(position).column

                i1 = i1 + 1
                j1 = j1 - 1

                if (i1 >= 0 && j1 >= 0 && i1 < span && j1 < span) {

                    if (matrixModel.user == 0) {
                        if (a == 1) {
                            break
                        }
                        a = 0;

                        count++;
                    } else if (matrixModel.user == 1) {
                        if (a == 0) {
                            break
                        }
                        a = 1
                        count++;
                    } else {
                        a = -1
                    }
                }

            }
        }

        if (count == span) {
            return true
        } else {
            return false
        }
    }
}